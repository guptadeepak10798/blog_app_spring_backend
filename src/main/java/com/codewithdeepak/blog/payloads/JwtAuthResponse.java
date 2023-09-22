package com.codewithdeepak.blog.payloads;

import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;
	
	private String role;
	
	private String loginUser;
	
	private int userId;
}
