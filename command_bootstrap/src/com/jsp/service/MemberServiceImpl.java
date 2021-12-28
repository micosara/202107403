package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.command.Criteria;
import com.jsp.command.PageMaker;
import com.jsp.dao.MemberDAO;
import com.jsp.dto.MemberVO;

public abstract class MemberServiceImpl implements MemberService{
	protected SqlSessionFactory sqlSessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	protected MemberDAO memberDAO;
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO = memberDAO;
	}
	
	
	@Override
	public List<MemberVO> getMemberList() throws Exception {
		SqlSession session= sqlSessionFactory.openSession(false);
		
		List<MemberVO> memberList = null;
		try {
			memberList = memberDAO.selectMemberList(session);
			
			session.commit();
			
		}catch(Exception e) {			
			session.rollback();
			e.printStackTrace();
			//......
			throw e;
		}finally {
			session.close();
		}
		
		return memberList;
	}


	@Override
	public List<MemberVO> getMemberList(Criteria cri) throws Exception {
		SqlSession session= sqlSessionFactory.openSession(false);
		
		List<MemberVO> memberList = null;
		try {
			memberList = memberDAO.selectMemberList(session,cri);
			
			session.commit();
			
		}catch(Exception e) {			
			session.rollback();
			e.printStackTrace();
			//......
			throw e;
		}finally {
			session.close();
		}
		
		return memberList;
	}


	@Override
	public Map<String, Object> getMemberListPage(Criteria cri) throws Exception {
		
		Map<String,Object> dataMap =null;
		
		SqlSession session= sqlSessionFactory.openSession(false);
		
		try {
			List<MemberVO> memberList=null;
			PageMaker pageMaker = null;
			
			memberList = memberDAO.selectMemberList(session, cri);
			pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			pageMaker.setTotalCount(memberDAO.selectMemberListCount(session));
			
			
			dataMap = new HashMap<String,Object>();
			dataMap.put("memberList", memberList);
			dataMap.put("pageMaker",pageMaker);
			
			session.commit();
		}catch(Exception e) {			
			session.rollback();
			e.printStackTrace();
			//......
			throw e;
		}finally {
			session.close();
		}
		
		return dataMap;
	}


}






