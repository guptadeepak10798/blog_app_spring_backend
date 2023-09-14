package com.codewithdeepak.blog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithdeepak.blog.payloads.JwtAuthRequest;
import com.codewithdeepak.blog.payloads.JwtAuthResponse;
import com.codewithdeepak.blog.payloads.UserDto;
import com.codewithdeepak.blog.security.JwtTokenHelper;
import com.codewithdeepak.blog.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("api/v1/auth/")
public class AuthController {

	@Autowired
	private JwtTokenHelper jwtTokenHelper;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request)  {

		this.authenticate(request.getUsername(), request.getPassword());
		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getUsername());
		System.out.println("userDetails ==> "+userDetails);
		String token = this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse response = new JwtAuthResponse();
		response.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password)  {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				username, password);
		Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		System.out.println("authenticate =>> "+authenticate.toString());
		/*
		 * try {
		 * this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		 * } catch (BadCredentialsException e) { // TODO Auto-generated catch block
		 * System.out.println("Invalid Details!!"); throw new
		 * Exception("Invalid username or password !! "); }
		 */
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
		String password = userDto.getPassword();
		System.out.println("password ==> "+password);
		UserDto registeredUser = this.userService.registerNewUser(userDto);
		return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
		
	}
	
}
