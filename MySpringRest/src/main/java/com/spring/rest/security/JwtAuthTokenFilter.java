package com.spring.rest.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthTokenFilter extends OncePerRequestFilter{

	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	MyUserDetailsService userDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// Get JWT
		String jwt = getJwt(request);
		if (jwt != null && jwtProvider.validateJwtToken(jwt))
		{
			String userName = jwtProvider.getUserNameFromJwtToken(jwt);
			UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
			
			UsernamePasswordAuthenticationToken authentication = 
					new UsernamePasswordAuthenticationToken(
							userDetails, 
							null, 
							userDetails.getAuthorities());
			// Tell other filters and servlets the request details infos
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			// Set the spring security context authentication
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			
			filterChain.doFilter(request, response);
			
		}
		
	}
	
	
	// Get the token
	private String getJwt(HttpServletRequest request)
	{
		String authheader = request.getHeader("Authorization");
		
		if (authheader != null && authheader.startsWith("Bearer "))
		{
			return authheader.replace("Bearer ", "");
		}
		else
			return null;
	}

}
