package com.ap.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ap.service.MyUserDetailsService;

@Configuration
@EnableWebSecurity
//to take control to handelour own securiy instead of spring
public class SecurityConfig {
	

	private  final UserDetailsService userdetailservice;
	private final JwtAuthenticationFilter jwtAuthenticationFilter;
	public SecurityConfig(UserDetailsService userdetailservice,JwtAuthenticationFilter jwtAuthenticationFilter)
	{
		this.userdetailservice=userdetailservice;
		this.jwtAuthenticationFilter=jwtAuthenticationFilter;
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity seq) throws Exception
	
	{
		seq.
			csrf(csrf->csrf.disable())
			.authorizeHttpRequests(req->req.requestMatchers("reg","login").permitAll().
					anyRequest().authenticated())
			
			.httpBasic(Customizer.withDefaults()).
			addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
			
			
			
			//basic authentication
		return seq.build();
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder(14);
	}
	
	@Bean
	public AuthenticationProvider authprovider()
	{
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(userdetailservice);
		provider.setPasswordEncoder(bCryptPasswordEncoder());
		return provider;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception
	{
		
		return configuration.getAuthenticationManager();
	}

}
