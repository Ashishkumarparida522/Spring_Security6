package com.ap.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ap.entity.User;
import com.ap.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
	
	public UserService(BCryptPasswordEncoder bCryptPasswordEncoder,AuthenticationManager authenticationManager,JwtService jwtService)
	{
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
		this.authenticationManager=authenticationManager;
		this.jwtService = jwtService;
	}
	
	
	public User saveUser(User user)
	{
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return repo.save(user);
	}


	public String verifyUser(User user) {
		// TODO Auto-generated method stub
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
		
		
		//var u = repo.findBy)UserName(user.getUserName());
		
		
		if(authenticate.isAuthenticated())
	    return jwtService.generateToken(user);
		
	return "failure";
	
	}
	

}
