package com.ap.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ap.entity.User;
import com.ap.repository.UserRepository;

@Component
public class MyUserDetailsService implements UserDetailsService {

	private final UserRepository userRepo;
	 public MyUserDetailsService(UserRepository userRepo)
		
		{
			this.userRepo=userRepo;
		}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepo.findByUserName(username);
		if(Objects.isNull(user))
		{
			System.out.println("user not avaliable");
			throw new UsernameNotFoundException("user not found");
		}
		return  new CustomUserDetails(user);
	}

}
