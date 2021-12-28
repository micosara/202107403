package kr.or.ddit.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

import kr.or.ddit.utils.ExceptionLoggerHelper;

public class LoginSuccessHandler extends 
				SavedRequestAwareAuthenticationSuccessHandler{

	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		//UserDetails userDetail = (UserDetails)authentication.getDetails();	
		String id =  (String)authentication.getPrincipal(); //userDetail.getUsername();
		MemberVO loginUser=null;
		try {
			loginUser = memberService.getMember(id);
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionLoggerHelper.write(request, e, memberService);			
			//response.getWriter() 메세지 전송 (script)
		}		
		
		HttpSession session = request.getSession();		
		session.setAttribute("loginUser", loginUser);
		session.setMaxInactiveInterval(10);
		
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
	
	

}
