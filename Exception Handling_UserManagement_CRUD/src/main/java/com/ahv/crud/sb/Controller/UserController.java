package com.ahv.crud.sb.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.ahv.crud.sb.Exception.ErrorDetails;
import com.ahv.crud.sb.Exception.ResourceNotFoundException;
import com.ahv.crud.sb.dto.UserDto;
import com.ahv.crud.sb.service.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {

	// Instance Variable
	@Autowired
	private UserService userService;

	// Constructor
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	// http://localhost:8080/api/users
	@PostMapping
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto user) {
		UserDto savedUser = userService.createUser(user);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	// http://localhost:8080/api/users/1
	@GetMapping("{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
		UserDto user = userService.getUserById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	// http://localhost:8080/api/users
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	// http://localhost:8080/api/users/1
	@PutMapping("{id}")
	// http://localhost:8080/api/users/1
	public ResponseEntity<UserDto> updatedUser(@PathVariable("id") Long userId, @RequestBody UserDto user) {
		user.setId(userId);
		UserDto updatedUser = userService.updateUser(user);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	// http://localhost:8080/api/users/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
	}

//	@ExceptionHandler(ResourceNotFoundException.class)
//	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
//			WebRequest webRequest) {
//
//		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(),
//				webRequest.getDescription(false), "USER_NOT_FOUND");
//		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//	}
}
