package com.java.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.utils.Message;
import com.java.view.View;

public class LoginServlet extends HttpServlet {
	
	private Message message;
	
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String propertiesPath = config.getInitParameter("message.properties");
		
		message = Message.getInstance();
		try {
			message.initMessage(propertiesPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 화면 전송
		
		View.loginForm(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그인 처리
		
		//입력
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");		
		boolean result = true;
		
		
		//처리
		result = id.equals("mimi") && pwd.equals("mimi");
				
				
		//출력
		if(result) { //로그인 성공
			View.alert(response, message.getMessage("message.loginSuccess")); 
		}else {
			View.alert(response, message.getMessage("message.loginFail"));
			View.loginForm(request, response);
		}
		
		
	}

}






