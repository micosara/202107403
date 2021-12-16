package com.jsp.action.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class MemberModifyFormAction implements Action {

	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = "/member/modify";
		try {
			String id = request.getParameter("id");
			MemberVO member = memberService.getMember(id);
			request.setAttribute("member", member);
		}catch(Exception e) {
			e.printStackTrace();
			// error 처리....
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);;
			url=null;
		}
		
		
		return url;
	}

}




