package com.java.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.dto.MemberVO;

@WebServlet("/member/detail")
public class DetailMemberServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//입력
		String id = request.getParameter("id");		
		
		//처리
		//MemberVO member = service.getMember(id);
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPwd(id);
		request.setAttribute("member", member);
		
		//출력 : forwarding
		request.getRequestDispatcher("/member/detail.jsp").forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
