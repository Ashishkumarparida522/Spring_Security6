package com.ap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ap.entity.User;
import com.ap.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public UserService(BCryptPasswordEncoder bCryptPasswordEncoder)
	{
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
	}
	
	
	public User saveUser(User user)
	{
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return repo.save(user);
	}
	

}
