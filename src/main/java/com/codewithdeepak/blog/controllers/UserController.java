package com.codewithdeepak.blog.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codewithdeepak.blog.payloads.ApiResponse;
import com.codewithdeepak.blog.payloads.UserDto;
import com.codewithdeepak.blog.services.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:4200/") 
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	

	/* POST -> CREATE USER 
	 * @Valid is used as validation
	 * 
	 * */
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto createUserDto = this.userService.createUser(userDto);
		return new ResponseEntity<>(createUserDto, HttpStatus.CREATED);

	}

	/* PUT -> UPDATE USER */
	@PutMapping("/{userId}") // {userId} is called as pathvariable
	public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto, @PathVariable Integer userId) {
		UserDto updatedUser = this.userService.updateUser(userDto, userId);
		return ResponseEntity.ok(updatedUser);

	}

	/*ADMIN ACCESS ONLY DELETE -> DELETE USER */
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer uid) {

//		System.out.println("uid====="+uid);
		this.userService.deleteUser(uid);
//		return ResponseEntity.ok(Map.of("message","User deleted successfully"));
		/* if we want to send status code then */
//		return new ResponseEntity(Map.of("message","User deleted successfully"),HttpStatus.OK);
		/* If we want to use api response class to send response */
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);

	}

//	@PreAuthorize("hasRole('ADMIN')")
	/* GET -> GET All USERS */
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> getAllUsers() {
		return ResponseEntity.ok(this.userService.getAllUsers());
	}

	/* GET -> GET Single USERS */
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer uid) {
		return ResponseEntity.ok(this.userService.getUserById(uid));
	}
	

	
}
