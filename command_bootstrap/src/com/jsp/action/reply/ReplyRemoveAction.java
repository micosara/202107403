package com.jsp.action.reply;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.command.PageMaker;
import com.jsp.command.ReplyRemoveCommand;
import com.jsp.command.SearchCriteria;
import com.jsp.controller.HttpRequestParameterAdapter;
import com.jsp.service.ReplyService;

public class ReplyRemoveAction implements Action {

	private ReplyService replyService;

	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = null;

		try {
			ReplyRemoveCommand removeCMD = (ReplyRemoveCommand) HttpRequestParameterAdapter
					.execute(request,ReplyRemoveCommand.class);

			replyService.removeReply(removeCMD.getRnoInt());

			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(new SearchCriteria());
			pageMaker.setTotalCount(replyService.getReplyListCount(removeCMD.getBnoInt()));

			int realEndPage = pageMaker.getRealEndPage();

			int page=removeCMD.getPageInt();
			if (page > realEndPage) {			
				page = realEndPage;
			}

			PrintWriter out = response.getWriter();
			out.print(page);
			out.close();

		} catch (Exception e) {
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return url;
	}

}
