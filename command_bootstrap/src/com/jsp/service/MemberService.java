package com.jsp.service;

import java.util.List;
import java.util.Map;

import com.jsp.command.Criteria;
import com.jsp.dto.MemberVO;

public interface MemberService {

	
	public List<MemberVO> getMemberList()throws Exception;
	
	public List<MemberVO> getMemberList(Criteria cri)throws Exception;
	
	public Map<String,Object> getMemberListPage(Criteria cri)throws Exception;
	
	
	public MemberVO getMember(String id) throws Exception;
	
	// 회원등록
	public void regist(MemberVO member) throws Exception;
}








