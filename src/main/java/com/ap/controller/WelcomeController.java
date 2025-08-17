package com.ap.controller;

import java.net.http.HttpRequest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class WelcomeController {
	
	@GetMapping("/welcome")
	public String welcome()
	{
		return "hey welcome_Buddy";
	}
		
	@GetMapping("/csrf")
	public CsrfToken getToken(HttpServletRequest req)
	{
		return (CsrfToken)req.getAttribute("_csrf");
		
	}
}
