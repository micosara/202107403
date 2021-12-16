package kr.or.ddit.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.dao.MemberDAO;
import kr.or.ddit.dto.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:kr/or/ddit/context/root-context.xml")
@Transactional
public class TestMemberDAOImpl {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private SqlSession sqlSession;
	
	
	@Test
	public void testSelectMember()throws Exception{
		String id="mimi";
		
		MemberVO member = memberDAO.selectMemberById(sqlSession,id);
		
		Assert.assertEquals(id,member.getId());
	}
	
	@Test
	@Rollback
	public void testInsertMember()throws SQLException{
		MemberVO testMember = new MemberVO();
		testMember.setId("kaka");
		testMember.setPwd("kaka");
		testMember.setName("kaka");
		testMember.setEmail("kaka@kaka.net");
		testMember.setPhone("000-0000-0000");
		testMember.setPicture("noImage.jpg");
		testMember.setAuthority("ROLE_USER");
		
		memberDAO.insertMember(sqlSession,testMember);
		
		MemberVO result = memberDAO.selectMemberById(sqlSession,testMember.getId());
		
		Assert.assertEquals(testMember.getId(),result.getId());
	}
}








