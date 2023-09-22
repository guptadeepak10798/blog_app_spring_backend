package com.codewithdeepak.blog.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codewithdeepak.blog.config.AppConstants;
import com.codewithdeepak.blog.entities.Role;
import com.codewithdeepak.blog.entities.User;
import com.codewithdeepak.blog.exceptions.ResourceNotFoundException;
import com.codewithdeepak.blog.payloads.JwtAuthRequest;
import com.codewithdeepak.blog.payloads.RoleDto;
import com.codewithdeepak.blog.payloads.UserDto;
import com.codewithdeepak.blog.services.UserService;
import com.codewithdeepak.blog.repositories.*;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		
//		RoleDto normalUserRole = new RoleDto();
//		normalUserRole.setId(502); // Set the ID of the "NORMAL USER" role
//		normalUserRole.setName("ROLE_NORMAL"); // Set the name of the "NORMAL USER" role
//		Set<RoleDto> roles = new HashSet<>();
//		roles.add(normalUserRole);
//		userDto.setRoles(roles);
		
		User user = this.dtoToUser(userDto);// converting userdto to user
		User savedUser = this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		System.out.println("userDto ===>> " + userDto);
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id ", userId));
		System.out.println("user ===>> " + user);
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		user.getRoles().add(role);
		User updatedUser = this.userRepo.save(user);
		UserDto userDto1 = this.userToDto(updatedUser);
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));

		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(a -> this.userToDto(a)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User userTobeDeleted = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(userTobeDeleted);
	}

	/* Model/Entity mappers (ek entity ko dusre entity me map) */

	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto,User.class);
		/*
		 * User user = new User(); user.setId(userDto.getId());
		 * user.setName(userDto.getName()); user.setEmail(userDto.getEmail());
		 * user.setPassword(userDto.getPassword()); user.setAbout(userDto.getAbout());
		 */
		return user;
	}

	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user,UserDto.class);
		/*
		 * UserDto userDto = new UserDto(); userDto.setId(user.getId());
		 * userDto.setName(user.getName()); userDto.setEmail(user.getEmail());
		 * userDto.setPassword(user.getPassword()); userDto.setAbout(user.getAbout());
		 */
		return userDto;

	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));//encoded password here
		//roles 
		Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
		System.out.println("register user default role setup => "+role);
		user.getRoles().add(role);
		User newUser = this.userRepo.save(user);
		Set<Role> roles = newUser.getRoles();
		System.out.println("roles size ========>> "+roles.size());
			System.out.println("Iterating over Set using for-each loop:");
		    for(Role language : roles) {
		      System.out.print(language);
		      System.out.print(", ");
		    }
		return this.modelMapper.map(newUser, UserDto.class);
	}

	
	@Override
	public Set<Role> getUserRole(JwtAuthRequest request) {
		
		User user = this.userRepo.findByEmail(request.getUsername()).get();
		Set<Role> roles = user.getRoles();
		System.out.println("roles ===>"+roles);
		return roles;
//				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
//		return this.userToDto(user).toString();
//		return user.toString();
	}
}
