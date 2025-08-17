package com.ap.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ap.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends  OncePerRequestFilter{
		
	
	private final JwtService jwtService;
	private final UserDetailsService detailsService;
	public JwtAuthenticationFilter( JwtService jwtService,UserDetailsService detailsService)
	{
		this.jwtService=jwtService;
		this.detailsService = detailsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException
	{
		String authHeader=request.getHeader("Authorization");
		if(authHeader==null || !authHeader.startsWith("Bearer"))
		{
			filterChain.doFilter(request, response);
			return;
		}
		
		final String jwt=authHeader.substring(7);
		final String userName=jwtService.extractUserName();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(userName!= null && authentication==null)
		{
			UserDetails userdetails = detailsService.loadUserByUsername(userName);
			if(jwtService.isTokenValid(jwt,userdetails))
			{
				UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken( userdetails,null,userdetails.getAuthorities());
			
				authenticationToken.setDetails(
						new WebAuthenticationDetailsSource().buildDetails(request)
						
						);
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
			
		}
		else
			filterChain.doFilter(request, response);
		
	}

}
