package com.jsp.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.BoardVO;
import com.jsp.service.BoardService;

public class BoardModifyFormAction implements Action {

	private BoardService boardService;

	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = "/board/modify";

		try {
			int bno = Integer.parseInt(request.getParameter("bno"));

			BoardVO board = boardService.getBoardForModify(bno);

			request.setAttribute("board", board);
		} catch (Exception e) {
			e.printStackTrace();
			url = null;
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
		}

		return url;
	}

}
