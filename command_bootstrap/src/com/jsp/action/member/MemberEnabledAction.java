package com.jsp.action.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.service.MemberServiceForModify;

public class MemberEnabledAction implements Action{


	private MemberServiceForModify memberServiceForModify;
	public void setMemberServiceForModify(MemberServiceForModify memberServiceModify) {
		this.memberServiceForModify = memberServiceForModify;
	}
	
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url=null;
		
		String id = request.getParameter("id");
		int enabled = Integer.parseInt(request.getParameter("enabled"));
		
		try {
			memberServiceForModify.enabled(id, enabled);
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('"+id+"님의 상태가 변경되었습니다.');");
			out.println("location.href='detail?id="+id+"';");
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
