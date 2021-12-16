package com.jsp.action.reply;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.command.CriteriaCommand;
import com.jsp.command.SearchCriteria;
import com.jsp.controller.HttpRequestParameterAdapter;
import com.jsp.controller.JSONResolver;
import com.jsp.service.ReplyService;

public class ReplyListAction implements Action {

	private ReplyService replyService;

	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = null;

		try {
			int bno = Integer.parseInt(request.getParameter("bno"));

			CriteriaCommand criCMD = (CriteriaCommand) HttpRequestParameterAdapter.execute(request,
					CriteriaCommand.class);

			SearchCriteria cri = criCMD.toSearchCriteria();

			Map<String, Object> dataMap = replyService.getReplyList(bno, cri);

			JSONResolver.view(response, dataMap);
			
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
		}
		return url;
	}

}
