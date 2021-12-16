package com.jsp.action.notice;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.service.NoticeService;

public class NoticeRemoveAction implements Action {

	private NoticeService noticeService;

	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = "/notice/remove_success";
		try {
			int nno = Integer.parseInt(request.getParameter("nno"));

			noticeService.remove(nno);
		} catch (Exception e) {
			e.printStackTrace();
			url = null;
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
		}

		return url;
	}

}
