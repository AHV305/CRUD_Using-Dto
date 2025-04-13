package com.ahv.crud.sb.service;

import java.util.List;

import com.ahv.crud.sb.dto.UserDto;

public interface UserService {

	UserDto createUser(UserDto user);
	UserDto getUserById(Long userId);
	List<UserDto>getAllUsers();
	UserDto updateUser(UserDto user);
	void deleteUser(Long userId);
}
