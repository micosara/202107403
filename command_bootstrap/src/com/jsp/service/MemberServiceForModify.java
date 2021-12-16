package com.jsp.service;

import javax.servlet.http.HttpServletRequest;

import com.jsp.dto.MemberVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIDException;

public interface MemberServiceForModify extends MemberService {

	
	// 회원수정
	void modify(MemberVO member) throws Exception;
	
	// 회원삭제
	void remove(String id) throws Exception;
	
	// 회원 상태변경
	void enabled(String id, int enabled)throws Exception;
	
	// 회원 로그인
	void login(String id, String pwd) throws NotFoundIDException, 
											 InvalidPasswordException, 
											 Exception;
}










