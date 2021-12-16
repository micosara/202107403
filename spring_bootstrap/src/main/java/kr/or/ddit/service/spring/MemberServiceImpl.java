package kr.or.ddit.service.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsp.command.Criteria;
import com.jsp.command.PageMaker;
import com.jsp.command.SearchCriteria;
import com.jsp.dto.MemberVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIDException;
import com.jsp.service.MemberServiceForModify;

import kr.or.ddit.dao.spring.MemberDAOBean;

public class MemberServiceImpl implements MemberServiceForModify{

	
	private MemberDAOBean memberDAOBean;
	public void setMemberDAOBean(MemberDAOBean memberDAOBeanBean) {
		this.memberDAOBean = memberDAOBeanBean;
	}
	
	@Override
	public MemberVO getMember(String id) throws Exception {

		MemberVO member = memberDAOBean.selectMemberById(id);
		return member;
	}

	@Override
	public List<MemberVO> getMemberList() throws Exception {
		List<MemberVO> memberList = memberDAOBean.selectMemberList();
		return memberList;
	}

	@Override
	public List<MemberVO> getMemberList(Criteria cri) throws Exception {
		List<MemberVO> memberList = memberDAOBean.selectMemberList(cri);
		return memberList;
	}

	@Override
	public Map<String, Object> getMemberListPage(Criteria cri) throws Exception {

		SearchCriteria searchCri = (SearchCriteria)cri;
		Map<String, Object> dataMap = new HashMap<String, Object>();

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(memberDAOBean.selectMemberListCount(searchCri));

		List<MemberVO> memberList = memberDAOBean.selectMemberList(searchCri);

		dataMap.put("memberList", memberList);
		dataMap.put("pageMaker", pageMaker);

		return dataMap;
	}

	@Override
	public void regist(MemberVO member) throws Exception {

		memberDAOBean.insertMember(member);
	}

	@Override
	public void modify(MemberVO member) throws Exception {
		memberDAOBean.updateMember(member);
	}

	@Override
	public void remove(String id) throws Exception {

		memberDAOBean.deleteMember(id);

	}	

	@Override
	public void enabled(String id,int state) throws Exception {

		memberDAOBean.enabledMember(id,state);
	}

	


	@Override
	public void login(String id, String pwd) throws NotFoundIDException, InvalidPasswordException, Exception {
		MemberVO member = memberDAOBean.selectMemberById(id);
		if (member == null)
			throw new NotFoundIDException();
		if (!pwd.equals(member.getPwd()))
			throw new InvalidPasswordException();
		
	}
	
	
	
}
