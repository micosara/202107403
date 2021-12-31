package kr.or.ddit.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.josephoconnell.html.HTMLInputFilter;
import com.jsp.command.SearchCriteria;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;

import kr.or.ddit.command.PdsModifyCommand;
import kr.or.ddit.command.PdsRegistCommand;
import kr.or.ddit.utils.GetAttachesByMultipartFileAdapter;

@Controller
@RequestMapping("/pds")
public class PdsController {

	//@Autowired
	@Resource(name="pdsService")
	private PdsService service;	

	@Resource(name = "fileUploadPath")
	private String fileUploadPath;
	
	@RequestMapping("/main")
	public void main() throws Exception {}

	@RequestMapping("/list")
	public ModelAndView list(SearchCriteria cri, ModelAndView mnv) throws Exception {
		String url = "pds/list";

		Map<String, Object> dataMap = service.getList(cri);

		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);

		return mnv;
	}
	
	@RequestMapping("/registForm")
	public ModelAndView registForm(ModelAndView mnv) throws Exception {
		String url = "pds/regist";
		mnv.setViewName(url);
		return mnv;
	}

	@RequestMapping(value = "/regist", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	public String regist(PdsRegistCommand registReq,						 
						 RedirectAttributes rttr) throws Exception {
		String url = "redirect:/pds/list.do";
		
		PdsVO pds = registReq.toPdsVO();
		
		// 저장 후 attach list 생성 PdsVO 인가 
		List<AttachVO> attachList 
			= GetAttachesByMultipartFileAdapter.save(registReq.getUploadFile(), fileUploadPath);
		pds.setAttachList(attachList);
		
		// XSS 적용
		pds.setTitle(HTMLInputFilter.htmlSpecialChars(pds.getTitle()));
		
		service.regist(pds);
		
		rttr.addFlashAttribute("from", "regist");
		return url;
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(int pno, String from, ModelAndView mnv) throws Exception {
		String url = "pds/detail";

		PdsVO pds = null;
		if (from != null && from.equals("list")) {
			pds = service.read(pno);
			url = "redirect:/pds/detail.do?pno=" + pno;
		} else {
			pds = service.getPds(pno);
		}

		// 파일명 재정의
		if (pds != null) {
			List<AttachVO> attachList = pds.getAttachList();
			if (attachList != null) {
				for (AttachVO attach : attachList) {
					String fileName = attach.getFileName().split("\\$\\$")[1];
					attach.setFileName(fileName);
				}
			}
		}
		mnv.addObject("pds", pds);
		mnv.setViewName(url);

		return mnv;
	}

	@RequestMapping("/modifyForm")
	public ModelAndView modifyForm(ModelAndView mnv, int pno) throws Exception {
		String url = "pds/modify";

		PdsVO pds = service.getPds(pno);

		// 파일명 재정의
		List<AttachVO> attachList = pds.getAttachList();
		if (attachList != null) {
			for (AttachVO attach : attachList) {
				String fileName = attach.getFileName().split("\\$\\$")[1];
				attach.setFileName(fileName);
			}
		}

		mnv.addObject("pds", pds);
		mnv.setViewName(url);

		return mnv;
	}
	
	@RequestMapping("/modify")
	public String modifyPOST(PdsModifyCommand modifyReq, 
							 HttpServletRequest request, 
							 RedirectAttributes rttr) throws Exception {
		String url = "redirect:/pds/detail.do";

		// 파일 삭제
		if (modifyReq.getDeleteFile() != null && modifyReq.getDeleteFile().length > 0) {
			for (String anoStr : modifyReq.getDeleteFile()) {
				int ano = Integer.parseInt(anoStr);
				AttachVO attach = service.getAttachByAno(ano);
				File deleteFile = new File(attach.getUploadPath(), attach.getFileName());
				if (deleteFile.exists()) {
					deleteFile.delete(); // File 삭제
				}
				service.removeAttachByAno(ano); // DB 삭제
			}
		}
		// 파일 저장
		List<AttachVO> attachList 
		= GetAttachesByMultipartFileAdapter.save(modifyReq.getUploadFile(), fileUploadPath);

		// PdsVO settting
		PdsVO pds = modifyReq.toPdsVO();		
		pds.setAttachList(attachList);
		pds.setTitle(HTMLInputFilter.htmlSpecialChars(pds.getTitle()));
		
		// DB 저장
		service.modify(pds);

		rttr.addFlashAttribute("from", "modify");
		rttr.addAttribute("pno", pds.getPno());

		return url;

	}
	
	@RequestMapping("/remove")
	public String remove(int pno, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/pds/detail.do";

		// 첨부파일 삭제
		List<AttachVO> attachList = service.getPds(pno).getAttachList();
		if (attachList != null) {
			for (AttachVO attach : attachList) {
				File target = new File(attach.getUploadPath(), attach.getFileName());
				if (target.exists()) {
					target.delete();
				}
			}
		}

		// DB삭제
		service.remove(pno);

		rttr.addFlashAttribute("from", "remove");
		rttr.addAttribute("pno", pno);

		return url;
	}

	
	@RequestMapping("/getFile")
	public String getFile(int ano,Model model) throws Exception {
		
		String url="downloadFile";
		
		AttachVO attach = service.getAttachByAno(ano);
		

		model.addAttribute("savedPath", attach.getUploadPath());
		model.addAttribute("fileName", attach.getFileName());		
	
		return url;
	}
}









