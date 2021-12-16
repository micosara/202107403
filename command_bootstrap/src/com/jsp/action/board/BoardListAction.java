package com.jsp.action.board;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.command.CriteriaCommand;
import com.jsp.command.SearchCriteria;
import com.jsp.controller.XSSHttpRequestParameterAdapter;
import com.jsp.service.BoardService;

public class BoardListAction implements Action {
	

	private BoardService boardService;
	public void setBoardService(BoardService boardService) {
		this.boardService = boardService;
	}

	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = "/board/list";
		
		try {

			CriteriaCommand criCMD 
			//= (CriteriaCommand)HttpRequestParameterAdapter.execute(request, CriteriaCommand.class);
			= (CriteriaCommand)XSSHttpRequestParameterAdapter.execute(request, CriteriaCommand.class,false);
			
			SearchCriteria cri = criCMD.toSearchCriteria();		
			
			Map<String, Object> dataMap = boardService.getBoardList(cri);
			request.setAttribute("dataMap", dataMap);
		}catch (Exception e) {			
			e.printStackTrace();
			response.sendError(response.SC_BAD_REQUEST);
			url=null;
		}
		
		return url;
	}

}
