package kr.or.ddit.service.spring;

import java.sql.SQLException;

import com.jsp.dto.MemberVO;

import kr.or.ddit.dao.spring.ScheduledMemberDAOBean;

public class ScheduledMemberServiceForModifyImpl extends MemberServiceImpl
		implements ScheduledMemberServiceForModify {

	private ScheduledMemberDAOBean scheduedMemberDAO;
	public void setScheduedMemberDAO(ScheduledMemberDAOBean scheduedMemberDAO) {
		this.scheduedMemberDAO = scheduedMemberDAO;
	}
	
	
	@Override
	public MemberVO getMemberByPicture(String picture) throws SQLException {
		MemberVO member = scheduedMemberDAO.selectMemberByPicuture(picture);
		return member;
	}

}
