//package com.spring.rest.security;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.core.userdetails.UserDetails;
//
//@EnableWebSecurity
//public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
//
//	@Autowired
//	BasicAuthenticationPoint basicAuthenticationPoint;
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception
//	{
//		http.
//		    authorizeRequests().
//		    anyRequest().
//		    authenticated();
//		
//		http.
//		    httpBasic().
//		      authenticationEntryPoint(basicAuthenticationPoint);
//	}
//	
////	@Bean
////	protected UserDetailsService userDetailsService() {
////		List<UserDetails> users = new ArrayList<UserDetails>();
////		users.add(User.withDefaultPasswordEncoder().username("mohamed").password("1234").roles("USER").build());
////		users.add(User.withDefaultPasswordEncoder().username("hamza").password("111222333").roles("ADMIN").build());
////		users.add(User.withDefaultPasswordEncoder().username("fatma").password("111").roles("USER").build());
////
////		return new InMemoryUserDetailsManager(users);
////		
////	}
//}
