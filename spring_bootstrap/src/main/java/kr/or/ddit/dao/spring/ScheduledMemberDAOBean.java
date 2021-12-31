package kr.or.ddit.dao.spring;

import java.sql.SQLException;

import com.jsp.dto.MemberVO;

public interface ScheduledMemberDAOBean extends MemberDAOBean {
	
	public MemberVO selectMemberByPicuture(String picture)
												throws SQLException;
}
