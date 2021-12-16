package com.spring.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.command.ParamCommand;

@Controller
public class TestController {

	// 일반 servlet 방식

	@RequestMapping(value = "/http", method = RequestMethod.GET)
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		int num = Integer.parseInt(request.getParameter("num"));

		System.out.println(id + ":" + pwd + ":" + num);
	}

	// parameter 방식
	@RequestMapping(value = "/param", method = RequestMethod.GET)
	public void getParam(@RequestParam(defaultValue="nana")String idd, String pwd, int num) throws IOException, ServletException {
		System.out.println(idd + ":" + pwd + ":" + num);
	}

	// command 방식
	@RequestMapping(value = "/command", method = RequestMethod.GET)
	public void getCommand(ParamCommand cmd) throws IOException, ServletException {
		
		System.out.println(cmd);
	}
}









