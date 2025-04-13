package com.ahv.crud.sb.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahv.crud.sb.dto.UserDto;
import com.ahv.crud.sb.entity.User;
import com.ahv.crud.sb.repository.UserRepository;
import com.ahv.crud.sb.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ModelMapper modelMapper;

	// Instance Variable
	@Autowired
	private UserRepository userRepository;

	//constructor
	public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
		super();
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
	}

	@Override
	public UserDto createUser(UserDto userDto) {

		// convert UserDto into User JPA Entity
		// User user = UserMapper.mapToUser(userDto);
		User user = modelMapper.map(userDto, User.class);

		User savedUser = userRepository.save(user);

		// converting User JPA entity to UserDto
		// UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
		UserDto savedUserDto = modelMapper.map(savedUser, userDto.getClass());
		return savedUserDto;
	}

	@Override
	public UserDto getUserById(Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		User user = optionalUser.get();
		// return UserMapper.mapToUserDto(user);
		return modelMapper.map(user, UserDto.class);
	}

	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		// return
		// users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());

		return users.stream().map((user) -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(UserDto user) {
		User existingUser = userRepository.findById(user.getId()).get();
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		User updatedUser = userRepository.save(existingUser);
		// return UserMapper.mapToUserDto(updatedUser);
		return modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}

}
