package com.example.cms.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.annotation.ResponseStatusExceptionResolver;

import com.example.cms.requestdto.UserRequest;
import com.example.cms.responsedto.UserResponse;
import com.example.cms.service.UserService;
import com.example.cms.utility.ResponseStructure;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}


	@PostMapping("/users/register")
	 public ResponseEntity<ResponseStructure<UserResponse>> registerUser(
			 @RequestBody UserRequest  userRequest) {
		 return userService.registerUser(userRequest);
	 }
	 
	 
	 @GetMapping("/test")
    public String test() {
    	return "Hello from cms";
    	
    }
	 public ResponseEntity<ResponseStructure<UserResponse>> deleteUser(@PathVariable int userId){
		 return userService.deleteUser(userId);
	 }
    
}
