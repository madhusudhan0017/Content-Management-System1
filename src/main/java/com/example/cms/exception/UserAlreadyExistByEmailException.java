package com.example.cms.exception;

import lombok.AllArgsConstructor;

@SuppressWarnings("serial")
@AllArgsConstructor
public class UserAlreadyExistByEmailException extends RuntimeException {

	private String message;

	public String getMessage() {
		return message;
	}

	public UserAlreadyExistByEmailException (String message) {
		this.message = message;
	}
	
}
