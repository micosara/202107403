package com.java.view;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class View {

	private static void header(PrintWriter out, String title) throws IOException, ServletException {
		
		out.println("<html>");
		out.println("<!DOCTYPE html>");
		out.println("<head>");
		out.print("<title>");
		out.println(title);
		out.println("</title>");
		out.println("</head>");
		out.println("<body>");
	}

	private static void footer(PrintWriter out) throws IOException, ServletException {
		out.println("</body>");
		out.println("</html>");
	}

	public static void loginForm(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		header(out,"Login Pager");
		
		out.println("<form action='login' method='post'>");
		out.println("아이디 : <input type='text' name='id' " + " value='" + (id != null ? id : "" )+ "' /><br/>");
		out.println("패스워드 : <input type='password' name='pwd' /><br/>");
		out.println("<input type='submit' value='로그인' />");
		out.println("</form>");
		
		
		footer(out);
	}

	public static void alert(HttpServletResponse response, String message) throws IOException, ServletException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
				
		out.println("<script>");
		
		out.print("alert('"+message+"');");
				
		out.println("</script>");
		
	}
	
	public static void customHTMLForBody(HttpServletResponse response, 
											String title, String html) throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		header(out,title);
		
		out.println(html);
		
		footer(out);
	}
}
