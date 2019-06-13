package com.spring.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.rest.model.Request;
import com.spring.rest.model.Role;
import com.spring.rest.model.User;
import com.spring.rest.repository.RoleRepository;
import com.spring.rest.security.JwtProvider;
import com.spring.rest.security.JwtResponse;

@RestController
@CrossOrigin(origins = "http://localhost:4201")
public class AuthRestAPIs {
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	com.spring.rest.repository.UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;


	
	@PostMapping("/SignIn")
	  public ResponseEntity<?> authenticateUser(@RequestBody Request loginRequest) {
	    Authentication authentication = authenticationManager.authenticate(
	        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
	 
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	    JwtProvider jwtProvider = new JwtProvider();
	    String jwt = jwtProvider.generateJwtToken(authentication);
	    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
	 
	    return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), 
	    		userDetails.getAuthorities().iterator().next().toString()));
	  }
	 
//	  @PostMapping("/Auth/SignUp")
//	  public boolean registerUser(@Valid @RequestBody Request signUpRequest) {
//	    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//	     return false;
//	    }
//	    User user = new User();
//	    user.setUsername(signUpRequest.getUsername());
//	    user.setPassword(signUpRequest.getPassword());
//	    user.setRole(roleRepository.findById(signUpRequest.getRoleId()).get());
//	    userRepository.save(user);
//	    return true;
//	  }
//	
	
	@PostMapping("/test")
	public boolean test()
	{
		return true;
	}
	
}
 