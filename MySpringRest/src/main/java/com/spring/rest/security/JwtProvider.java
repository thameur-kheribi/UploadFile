package com.spring.rest.security;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {
	
	final static int jwtExpiration = 8000;
	final static String jwtSecret = "SecretGhazelaTech"; 
	
	// Generate the jwt token
	public String generateJwtToken(Authentication authentication)
	{
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		
		return Jwts.builder()
				.setSubject(userPrincipal.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date( new Date().getTime() + jwtExpiration  ))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	
	// Validate token
	public boolean validateJwtToken(String authToken)
	{
		Jws<Claims> claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
		if (claims.getBody().getExpiration().after(new Date()))
		{ 
			return true;
		}
		else
		{
			return false;
		}
	}
	
	// Get User name from Token
    public String getUserNameFromJwtToken(String authToken)
    {
    	return Jwts.parser().
    			setSigningKey(jwtSecret).
    			parseClaimsJws(authToken).
    			getBody().
    			getSubject();

    }
	
	
}
