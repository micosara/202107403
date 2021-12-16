package com.jsp.action.common;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.controller.MultipartHttpServletRequestParser;
import com.jsp.controller.SaveFileResolver;
import com.jsp.exception.NotMultipartFormDataException;
import com.jsp.utils.GetUploadPath;

public class SummernoteUploadImgAction implements Action {

	// 업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 500; // 500KB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 5; // 5MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 10; // 10MB

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String url=null;
		try {
		// request parsing
		MultipartHttpServletRequestParser multi 
		= new MultipartHttpServletRequestParser(request, MEMORY_THRESHOLD,
											   MAX_FILE_SIZE, MAX_REQUEST_SIZE);
		// upload path
		String uploadPath = GetUploadPath.getUploadPath("summernote.img"); 
				
		// file save : get file list
		List<File> fileList 
		= SaveFileResolver.fileUpload(multi.getFileItems("file"), uploadPath);
		
		// response out
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		if (fileList.size() > 0) {
			for (File file : fileList) {
				out.print(request.getContextPath() + "/getImg.do?fileName=" 
			+ file.getName());
			}
		}
		}catch(NotMultipartFormDataException e) {
			e.printStackTrace();
			response.sendError(response.SC_BAD_REQUEST);
			
		}catch(Exception e) {
			e.printStackTrace();
			response.sendError(response.SC_INTERNAL_SERVER_ERROR);
		}
		
		return url;
	}

}






