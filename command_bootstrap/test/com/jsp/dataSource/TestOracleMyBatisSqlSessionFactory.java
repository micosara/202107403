package com.jsp.dataSource;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jsp.context.ApplicationContext;
import com.jsp.dto.MemberVO;
import com.jsp.listener.MockApplicationContextInitListener;

public class TestOracleMyBatisSqlSessionFactory {


	private SqlSessionFactory sqlSessionFactory;
	private SqlSession session;
	
	{
		MockApplicationContextInitListener mockListener 
							= new MockApplicationContextInitListener();
		mockListener.contextInitialized("classpath:com/jsp/context/application-context.xml");
		Map<String,Object> container = ApplicationContext.getApplicationContext();
		this.sqlSessionFactory = (SqlSessionFactory)container.get("sqlSessionFactory");
	}
	
	@Before
	public void init() {
		session = sqlSessionFactory.openSession();				
	}
	

	@Test
	public void testNotNullSession() {
		Assert.assertNotNull(session);
	}
	
	@Test
	public void testNotNullConnection() {
		Assert.assertNotNull(session.getConnection());
	}
	
	@Test
	public void testSQL() throws SQLException {
		List<MemberVO> memberList 
			= session.selectList("Member-Mapper.selectMemberList");
		Assert.assertEquals(4, memberList.size());
	}
	
	@After
	public void complete() {
		session.close();
	}
}







