package com.myschool.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myschool.auth.JwtUtil;
import com.myschool.auth.MyUserDetailsService;
import com.myschool.models.request.AuthenticationRequest;
import com.myschool.models.request.UserRequest;
import com.myschool.models.response.AuthenticateResponse;
import com.myschool.models.response.Response;
import com.myschool.serviceImpl.TeachersServiceImpl;

@RestController
public class Controller {
	@Autowired
	private AuthenticationManager authManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private TeachersServiceImpl componentService;

	@GetMapping("/auth/user")
	public Response save() {
		return new Response("Hello World");
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticateResponse> auth(@RequestBody AuthenticationRequest request) throws Exception {
		try {
			authManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password!", e);
		}
		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
		final String jwt = jwtUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticateResponse(jwt));
	}

	@PostMapping("/register")
	public ResponseEntity<Response> addUserByAdmin(@RequestBody UserRequest user) {
		String encryptPwd = passwordEncoder.encode(user.getPassword());
		user.setPassword(encryptPwd);
		return ResponseEntity.ok(new Response(componentService.saveUser(user)));
	}

	@GetMapping("/auth/admin")
	public String admin() {
		return "admin works";
	}
}