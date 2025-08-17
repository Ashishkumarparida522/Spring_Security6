package com.ap.service;

import java.awt.RenderingHints.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ap.entity.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.KEY;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	
	private String secretKey=null;
	public String generateToken(User user) {
		// TODO Auto-generated method stub
		Map<String,Object>claims=new HashMap<>();
		return Jwts.builder().
				claims()
				.add(claims).
				subject(user.getUserName()).
				issuer("ap").
				issuedAt(new Date(System.currentTimeMillis())).
				expiration(new Date(System.currentTimeMillis()+60*10*1000)).
				and().
				signWith(generateKey()).compact();
				
	}
	
	private SecretKey generateKey()
	{
		byte[] decode = Decoders.BASE64.decode(getSecretKey());
		
		
		return Keys.hmacShaKeyFor(decode);
	}
	
	public String getSecretKey()
	{
		return secretKey="c13b4c4ad1c33280526db4cbea0f531fc73718c0514b345aa6d1ca67530cd3140a960183";
	}

	public static String extractUserName() {
		// TODO Auto-generated method stub
		return "";
	}

	public boolean isTokenValid(String jwt, UserDetails userdetails) {
		// TODO Auto-generated method stub
		return true;
	}

}
