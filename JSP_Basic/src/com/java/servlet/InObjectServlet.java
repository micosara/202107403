package com.java.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.dto.MemberVO;

/**
 * Servlet implementation class InObjectServlet
 */
@WebServlet("/inObject")
public class InObjectServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		ServletContext application = request.getServletContext();
		
		request.setAttribute("msg","유선종양이 아니라 혈종이랍니다.");
		session.setAttribute("msg","그래서 걱정안해도 된다네요..제거는 했으니");
		application.setAttribute("msg","정말 다행입니다...행복하게 오래살자..캔디야");
				
		Map<String,String> dataMap = new HashMap<String,String>();
		dataMap.put("girlGroup","티아라");	
		request.setAttribute("dataMap", dataMap);
		
		MemberVO member = new MemberVO();
		member.setId("mimi");
		member.setPwd("mimi");
		request.setAttribute("member", member);
		
		request.getRequestDispatcher("/inObject.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
