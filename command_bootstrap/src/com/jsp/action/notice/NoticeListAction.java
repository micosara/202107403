package com.jsp.action.notice;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.command.CriteriaCommand;
import com.jsp.command.SearchCriteria;
import com.jsp.controller.HttpRequestParameterAdapter;
import com.jsp.service.NoticeService;

public class NoticeListAction implements Action {
	
	private NoticeService noticeService;

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = "/notice/list";
		
		try {

			CriteriaCommand criCMD 
			= (CriteriaCommand)HttpRequestParameterAdapter.execute(request, CriteriaCommand.class);
			
			SearchCriteria cri = criCMD.toSearchCriteria();		
			
			Map<String, Object> dataMap = noticeService.getNoticeList(cri);
			request.setAttribute("dataMap", dataMap);
		}catch (Exception e) {			
			e.printStackTrace();
			response.sendError(response.SC_BAD_REQUEST);
			return null;
		}
		
		return url;
	}

}
