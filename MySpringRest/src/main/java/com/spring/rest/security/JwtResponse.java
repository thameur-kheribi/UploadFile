package com.spring.rest.security;

public class JwtResponse {
	private String token;
	private String username;
	private String authority;
	
	
	
	public JwtResponse(String token, String username, String authority) {
		super();
		this.token = token;
		this.username = username;
		this.authority = authority;
	}
	public JwtResponse()
	{}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	

}
