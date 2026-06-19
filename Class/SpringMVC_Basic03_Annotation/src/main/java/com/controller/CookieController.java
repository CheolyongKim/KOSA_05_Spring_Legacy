package com.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CookieController {
	@RequestMapping("/cookie/make.do")
	public String make(HttpServletResponse response) {
		response.addCookie(new Cookie("SPRINGUSER", "1004"));
		return "cookie/CookieMake";
	}
	
	@RequestMapping("/cookie/view.do")
	public String view(@CookieValue(value="SPRINGUSER", defaultValue="1007") String auth) {
		System.out.println("Client 브라우저에서 읽은 Cookie 값: " + auth);
		return "cookie/CookieView";
	}
}
