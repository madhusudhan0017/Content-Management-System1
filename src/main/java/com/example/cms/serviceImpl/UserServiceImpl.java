package com.example.cms.serviceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.cms.entity.User;
import com.example.cms.exception.UserAlreadyExistByEmailException;
import com.example.cms.exception.UserNotFoundException;
import com.example.cms.repository.UserRepository;
import com.example.cms.requestdto.UserRequest;
import com.example.cms.responsedto.UserResponse;
import com.example.cms.service.UserService;
import com.example.cms.utility.ResponseStructure;

@Service
public class UserServiceImpl implements UserService {

	private PasswordEncoder passwordEncoder;

	private UserRepository userRepository;

	private ResponseStructure<UserResponse> structure;


	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> registerUser(UserRequest userRequest) {
		if(userRepository.existsByEmail(userRequest.getEmail()))
			throw new UserAlreadyExistByEmailException("Failed to register user");
		User user = mapToUserEntity(userRequest, new User());
		userRepository.save(user);

		return ResponseEntity.ok(structure.setStatus(HttpStatus.CREATED.value())
				.setMessage("User registered successfully")
				.setBody(mapToUserResponse(userRepository.save(user))));
	}

	private User mapToUserEntity(UserRequest userRequest, User user) {
		user.setEmail(userRequest.getEmail());
		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		user.setDeleted(false);
		return user;
	}

	private UserResponse mapToUserResponse(User user) {
		return new UserResponse(user.getUserId(),
				user.getUserName(),
				user.getEmail(),
				user.getCreatedAt(),
				user.getLastModifiedAt());
	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(int userId) {

		return userRepository.findById(userId).map(user->{
			user.setDeleted(true);
			userRepository.save(user);
			return ResponseEntity.ok(structure.setStatus(HttpStatus.OK.value())
					.setMessage("User Deleted Successfully")
					.setBody(mapToUserResponse(userRepository.save(user))));
		}).orElseThrow(()-> new UserNotFoundException("User not found"));

	}

	@Override
	public ResponseEntity<ResponseStructure<UserResponse>> findById(int userId) {

		return userRepository.findById(userId).map(user->{
			return ResponseEntity.ok(structure.setStatus(HttpStatus.FOUND.value())
					.setMessage("User found By Id ")
					.setBody(mapToUserResponse(userRepository.save(user))));
		}).orElseThrow(()-> new UserNotFoundException("User not found"));

	}


}
