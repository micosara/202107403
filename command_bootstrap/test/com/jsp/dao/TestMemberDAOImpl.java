package com.jsp.dao;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jsp.context.ApplicationContext;
import com.jsp.dao.MemberDAO;
import com.jsp.dao.MemberDAOImpl;
import com.jsp.dto.MemberVO;
import com.jsp.listener.MockApplicationContextInitListener;


public class TestMemberDAOImpl {

	private SqlSessionFactory sqlSessionFactory;
	private MemberDAO memberDAO;
	private SqlSession session;
	
	{
		MockApplicationContextInitListener mockListener 
							= new MockApplicationContextInitListener();
		mockListener.contextInitialized("classpath:com/jsp/context/application-context.xml");
		Map<String,Object> container = ApplicationContext.getApplicationContext();
		this.sqlSessionFactory = (SqlSessionFactory)container.get("sqlSessionFactory");
		this.memberDAO = (MemberDAO)container.get("memberDAO");
	}
	
	@Before
	public void initSqlSession() {
		session = sqlSessionFactory.openSession();
	}
	
	@After
	public void closeSqlSession() {
		session.close();
	}
	
	@Test
	public void testSelectMemberList() throws Exception{
		List<MemberVO> memberList = memberDAO.selectMemberList(session);
		
		Assert.assertEquals(4, memberList.size());
	}
}












