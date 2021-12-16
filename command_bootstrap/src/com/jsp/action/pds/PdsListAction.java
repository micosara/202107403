package com.jsp.action.pds;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.command.CriteriaCommand;
import com.jsp.command.SearchCriteria;
import com.jsp.controller.XSSHttpRequestParameterAdapter;
import com.jsp.service.PdsService;

public class PdsListAction implements Action {

	private PdsService pdsService;

	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url = "/pds/list";

		try {
			CriteriaCommand criCMD
			// = (CriteriaCommand)HttpRequestParameterAdapter.execute(request,
			// CriteriaCommand.class);
					= (CriteriaCommand) XSSHttpRequestParameterAdapter.execute(request, CriteriaCommand.class, false);

			SearchCriteria cri = criCMD.toSearchCriteria();

			Map<String, Object> dataMap = pdsService.getList(cri);
			request.setAttribute("dataMap", dataMap);
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
			url = null;

		}
		return url;
	}

}
