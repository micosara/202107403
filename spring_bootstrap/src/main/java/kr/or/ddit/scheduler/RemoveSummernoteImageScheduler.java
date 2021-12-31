package kr.or.ddit.scheduler;

import java.io.File;
import java.util.List;

import com.jsp.command.SearchCriteria;
import com.jsp.dto.BoardVO;
import com.jsp.dto.NoticeVO;
import com.jsp.dto.PdsVO;

import kr.or.ddit.service.spring.ScheduledBoardService;
import kr.or.ddit.service.spring.ScheduledNoticeService;
import kr.or.ddit.service.spring.ScheduledPdsService;

public class RemoveSummernoteImageScheduler {

	private ScheduledNoticeService noticeService;
	private ScheduledBoardService boardService;
	private ScheduledPdsService pdsService;
	private String filePath;
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public void setNoticeService(ScheduledNoticeService noticeService) {
		this.noticeService = noticeService;
	}
	public void setBoardService(ScheduledBoardService boardService) {
		this.boardService = boardService;
	}
	public void setPdsService(ScheduledPdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	public void fileRemove()throws Exception {	
		
		boolean existFile = false;
		
		File dir = new File(filePath);
		File[] files = dir.listFiles();
		
		if(files!=null) for(File file : files) {				
			
			existFile = (noticeService.getNoticeByImage(file.getName())!=null)
					|| (boardService.getBoardByImage(file.getName())!=null)
					|| (pdsService.getPdsByImage(file.getName())!=null);
			
		/*	SearchCriteria cri  = new SearchCriteria();
			cri.setSearchType("c");  // content
			cri.setKeyword(file.getName());
			
			List<NoticeVO> noticeList 
			= (List<NoticeVO>)noticeService.getNoticeList(cri).get("noticeList");
			List<BoardVO> boardList 
			= (List<BoardVO>)boardService.getBoardList(cri).get("boardList");
			List<PdsVO> pdsList
			=(List<PdsVO>)pdsService.getList(cri).get("pdsList");
			
			existFile = noticeList.size()>0 || boardList.size()>0 || pdsList.size()>0;*/
			
			if(existFile) {
				System.out.println(file.getName()+" 은 사용하는 파일입니다.");
				continue;
			}else {
				System.out.println(file.getName()+" 은 사용하지 않습니다.");
				if(file.exists()) file.delete();
			}
		}
	}
	
}







