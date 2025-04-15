package com.ahv.crud.sb.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ahv.crud.sb.Exception.EmailAlreadyExistsException;
import com.ahv.crud.sb.Exception.ResourceNotFoundException;
import com.ahv.crud.sb.dto.UserDto;
import com.ahv.crud.sb.entity.User;
import com.ahv.crud.sb.repository.UserRepository;
import com.ahv.crud.sb.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	// Instance Variable
	@Autowired
	private ModelMapper modelMapper;

	// Instance Variable
	@Autowired
	private UserRepository userRepository;

	// constructor
	public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
		super();
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
	}

	@Override
	public UserDto createUser(UserDto userDto) {
		Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

		if (optionalUser.isPresent()) {
			throw new EmailAlreadyExistsException("Email Already Exists for user");
		}

		User user = modelMapper.map(userDto, User.class);
		User savedUser = userRepository.save(user);
		UserDto savedUserDto = modelMapper.map(savedUser, userDto.getClass());

		return savedUserDto;
	}

	@Override
	public UserDto getUserById(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

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

		User existingUser = userRepository.findById(user.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", user.getId()));

		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		User updatedUser = userRepository.save(existingUser);

		return modelMapper.map(updatedUser, UserDto.class);
	}

	@Override
	public void deleteUser(Long userId) {

		User existingUser = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		userRepository.deleteById(userId);
	}

}
