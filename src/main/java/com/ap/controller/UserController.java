package com.ap.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ap.entity.User;
import com.ap.repository.UserRepository;
import com.ap.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	@Autowired
	private UserRepository repo;
	
	@PostMapping("/reg")
	public ResponseEntity<String> regUser(@RequestBody User user)
	{
		String userName = service.saveUser(user).getUserName();
		
		return new ResponseEntity<String>(userName,HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody User user)
	{
//		
		return service.verifyUser(user);
	}

}
