package com.jsp.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.service.BoardService;

public class BoardRemoveAction implements Action {


	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url="/board/remove_success";
		try {
			int bno = Integer.parseInt(request.getParameter("bno"));
		
			boardService.remove(bno);
		}catch(Exception e) {
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
			url= null;
			e.printStackTrace();
		}
		
		
		return url;
	}

	
	
}
