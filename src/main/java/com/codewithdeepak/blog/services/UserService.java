package com.codewithdeepak.blog.services;

import java.util.List;
import java.util.Set;

import org.springframework.web.multipart.MultipartFile;

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
//	UserDto updateUser(UserDto userDto, MultipartFile file, Integer userId);
}
