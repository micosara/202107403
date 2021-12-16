package com.java.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/echo")
public class EchoServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Scanner scan = new Scanner(System.in);
		
		String inputStr = request.getParameter("inputStr");//scan.nextLine();
		
		
		response.setContentType("text/html;charset=utf-8");		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>");
		out.println("insert title here");
		out.println("</title>");
		out.println("<script>");
		out.println("alert('"+inputStr+"');");
		out.println("</script>");
		out.println("</head>");
		
		out.println("<body>");
		out.println("<h1>"+inputStr+"</h1>");
		out.println("</body>");
		
		out.println("</html>");
		
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
