package com.example.cms.exception;

public class BlogNotFoundByIdException extends RuntimeException {

	public String message;

	public BlogNotFoundByIdException(String message) {


		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
