package com.codewithdeepak.blog.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.codewithdeepak.blog.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * When User/Category is not available then exception is handled by below
	 * resourceNotFoundExceptionHandler method
	 **/
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
		String message = ex.getMessage();// throwable class ka method hai ye
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

	}

	/**
	 * When User is inserting or updating wrong inputs then exception is handled by
	 * below handlerMethodNotValidException method
	 **/
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handlerMethodNotValidException(MethodArgumentNotValidException ex) {
		Map<String, String> resp = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			resp.put(fieldName, message);
		});
		return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);

	}

	/**
	 * When User/Category is not available then exception is handled by below
	 * handlerMethodArgumentTypeMismatchException method
	 **/
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Map<String, Object>> handlerMethodArgumentTypeMismatchException(
			MethodArgumentTypeMismatchException ex) {
		Map<String, Object> resp = new HashMap<>();
		String name = ex.getName();
		String type = ex.getRequiredType().getSimpleName();
		Object value = ex.getValue();
		String message = String.format("'%s' should be a valid '%s' and '%s' isn't", name, type, value);
		resp.put("message", message);
		resp.put("success", false);
		return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * When User/Category is not available then exception is handled by below
	 * resourceNotFoundExceptionHandler method
	 **/
	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ApiResponse> invalidUserNameOrPasswordException(BadCredentialsException ex) {
		String message = ex.getMessage();// throwable class ka method hai ye
		ApiResponse apiResponse = new ApiResponse(message, false);
		return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.NOT_FOUND);

	}

}
