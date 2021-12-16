package com.jsp.action.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.service.MemberServiceForModify;

public class MemberRemoveAction implements Action {


	private MemberServiceForModify memberServiceForModify;
	public void setMemberServiceForModify(MemberServiceForModify memberServiceModify) {
		this.memberServiceForModify = memberServiceForModify;
	}
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = null;
		
		String id = request.getParameter("id");
		
		try {
			memberServiceForModify.remove(id);
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('"+id+"님 삭제되었습니다.');");
			out.println("window.close();");
			out.println("</script>");
			url=null;
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
			url=null;
		}
		
		return url;
	}

}
