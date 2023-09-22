package com.codewithdeepak.blog.services;

import java.util.List;
import java.util.Set;

import com.codewithdeepak.blog.entities.Role;
import com.codewithdeepak.blog.payloads.JwtAuthRequest;
import com.codewithdeepak.blog.payloads.UserDto;

public interface UserService {
	
	UserDto registerNewUser(UserDto user);
	UserDto createUser(UserDto user);
	UserDto updateUser(UserDto user, Integer userId);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void deleteUser(Integer userId);
	Set<Role> getUserRole(JwtAuthRequest request);
}
