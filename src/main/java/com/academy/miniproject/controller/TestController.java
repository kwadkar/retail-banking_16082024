package com.academy.miniproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.academy.miniproject.Utils.JwtUtil;
import com.academy.miniproject.model.AuthenticationRequest;
import com.academy.miniproject.model.AuthenticationResponse;
import com.academy.miniproject.service.MyUserDetailsService;

@RestController
public class TestController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	MyUserDetailsService myUserDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@GetMapping("/message")
	public String getMessage() {
		return "Good Morning";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> generateToken(@RequestBody AuthenticationRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
	                 new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword())
	         );
		} catch(BadCredentialsException e) {
			throw new Exception("Incorect username or password",e);
		}
		
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authRequest.getUserName());
		final String jwtToken = jwtUtil.generateToken(userDetails);
		System.out.println("JWT Token -->"+jwtToken);
		return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
	}
	
	/*
	 * authRequest - login details username password
	 * authenticationManager.authenticate() to checks username and password
	 * userDetails - returns pass username userdetails
	 * jwtUtil - call generatetoken() by passing userdetail instance
	 * return response in form of jwt token
	 * */

}
