package com.jsp.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.MemberVO;

public class CheckEnabledMemberFilter implements Filter {

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;

		String requestURI = httpReq.getRequestURI();
		String endPoint = requestURI.split("/")[requestURI.split("/").length - 1];
		
		String result ="";

		if (checkUrlsSet.contains(endPoint)) {
			HttpSession session = httpReq.getSession();
			MemberVO member = (MemberVO) session.getAttribute("loginUser");
			if(member==null) { // 인증필요
				result += "<script>"
						+"alert('로그인이 필요합니다.');"
						+"location.href='"+httpReq.getContextPath()+"/common/login';"
						+"</script>";
			}else {			
				int enabled = member.getEnabled();
	
				switch (enabled) {
				case 1: //활성상태
					chain.doFilter(request, response);
					return;
					
				case 0: //비활성상태
					result += "<script>"
							+"alert('비활성상태로 사용에 제한됩니다.');";
					if(request.getParameter("cw")!=null && 
							request.getParameter("cw").equals("t")) {
						result+="window.close();";
					}else {
						result+="history.go(-1);";
					}
					result+="</script>";
					break;
				}	
			}
			
			httpRes.setContentType("text/html;charset=utf-8");
			PrintWriter out = httpRes.getWriter();
			out.print(result);
			
		}else {		
			chain.doFilter(request, response);
		}
	}

	private Set<String> checkUrlsSet = new HashSet<String>();

	public void init(FilterConfig fConfig) throws ServletException {
		String checkUrlsParam = fConfig.getInitParameter("checkUrl");

		StringTokenizer token = new StringTokenizer(checkUrlsParam, ",");

		while (token.hasMoreTokens()) {
			checkUrlsSet.add(token.nextToken());
		}
	}

}
