package com.spring.tiles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@RequestMapping("/test.do")
	public String test() {
		return "test";
	}
	
	/*
	 * Tiles를 사용(header, left, footer 포함)
	 */
	@RequestMapping("/testPage.do")
	public String testPage(@RequestParam(defaultValue="true")boolean tiles,
						   @RequestParam(defaultValue="true")boolean footer) {
		
		String url="test";
		
		if(tiles) url+=".page";
		
		else if(!footer) url+=".no";
		
		return url;
	}
	

	@RequestMapping("/testNo.do")
	public String testNo() {
		return "test.no";
	}
	
}








