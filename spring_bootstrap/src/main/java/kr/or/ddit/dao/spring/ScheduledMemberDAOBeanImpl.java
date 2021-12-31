package kr.or.ddit.dao.spring;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.MemberVO;

public class ScheduledMemberDAOBeanImpl extends MemberDAOImplTemplate implements ScheduledMemberDAOBean {

	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Override
	public MemberVO selectMemberByPicuture(String picture) throws SQLException {
		MemberVO member=null;
		
		member = sqlSession.selectOne("Member-Mapper.selectMemberByPicture",picture);
		
		return member;
	}

}






