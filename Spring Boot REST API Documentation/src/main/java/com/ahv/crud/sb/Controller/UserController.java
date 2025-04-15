package com.ahv.crud.sb.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ahv.crud.sb.dto.UserDto;
import com.ahv.crud.sb.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Tag(
		name="CRUD REST API's for User Resource",
		description ="CRUD REST APIs - Create User, Update User, Get User, Get All users, Delete User"
		)
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

	
	@Operation(
			summary ="Create User REST API",
			description ="Create User REST API is used to save user in a database"
			)
	@ApiResponse(
			responseCode ="201",
			description ="HTTP Status 201 CREATED"
			)
	// http://localhost:8080/api/users
	@PostMapping
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
		UserDto savedUser = userService.createUser(user);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	@Operation(
			summary ="Get User By ID REST API",
			description ="Get User By ID REST API is used to get a single user from the database"
			)
	@ApiResponse(
			responseCode ="200",
			description ="HTTP Status 200 SUCCESS"
			)
	// http://localhost:8080/api/users/1
	@GetMapping("{id}")
	public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long userId) {
		UserDto user = userService.getUserById(userId);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	
	@Operation(
			summary ="Get All Users REST API",
			description ="Get All Users REST API is used to get all users from the database"
			)
	@ApiResponse(
			responseCode ="200",
			description ="HTTP Status 200 SUCCESS"
			)
	// http://localhost:8080/api/users
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	
	@Operation(
			summary ="Update User REST API",
			description ="Update User REST API is used to update a particular user in the database"
			)
	@ApiResponse(
			responseCode ="200",
			description ="HTTP Status 200 SUCCESS"
			)
	// http://localhost:8080/api/users/1
	@PutMapping("{id}")
	// http://localhost:8080/api/users/1
	public ResponseEntity<UserDto> updatedUser(@PathVariable("id") Long userId, @RequestBody @Valid UserDto user) {
		user.setId(userId);
		UserDto updatedUser = userService.updateUser(user);
		return new ResponseEntity<>(updatedUser, HttpStatus.OK);
	}

	@Operation(
			summary ="DELETE User REST API",
			description ="DELETE User REST API is used to DELETE a particular user from the database"
			)
	@ApiResponse(
			responseCode ="200",
			description ="HTTP Status 200 SUCCESS"
			)
	// http://localhost:8080/api/users/1
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
		userService.deleteUser(userId);
		return new ResponseEntity<>("User successfully deleted!", HttpStatus.OK);
	}

}
