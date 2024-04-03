package com.example.cms.exception;

public class TopicNotSpecifiedException extends RuntimeException {
	
	private String message;

	@Override
	public String getMessage() {
		return message;
	}


	public TopicNotSpecifiedException(String message) {
		super();
		this.message = message;
	}
	

}
