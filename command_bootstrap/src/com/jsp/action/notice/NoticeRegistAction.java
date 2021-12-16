package com.jsp.action.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.controller.XSSHttpRequestParameterAdapter;
import com.jsp.controller.XSSResolver;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;

public class NoticeRegistAction implements Action{
	
	private NoticeService noticeService;
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url="/notice/regist_success";
		
		try {
			
			NoticeVO notice 
			= (NoticeVO)XSSHttpRequestParameterAdapter.execute(request, NoticeVO.class,true);
			
			notice.setContent(request.getParameter("content"));
			
			noticeService.regist(notice);
			
		}catch(Exception e) {
			//url = "notice/regist_fail"; 동기식.....
			e.printStackTrace();
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
			url=null;
		}
		return url;
	}
}







