package com.ahv.crud.sb.mapper;

import com.ahv.crud.sb.dto.UserDto;
import com.ahv.crud.sb.entity.User;

public class UserMapper {

	public static UserDto mapToUserDto(User user) {
		UserDto userDto = new UserDto(
				user.getId(),
				user.getFirstName(),
				user.getLastName(),
				user.getEmail()
				);
		return userDto;
	}
	
	//convert UserDto into user JPA Entity
	public static User mapToUser(UserDto userDto) {
		User user = new User(
				userDto.getId(),
				userDto.getFirstName(),
				userDto.getLastName(),
				userDto.getEmail()
				);
		
		return user;
	}
}
