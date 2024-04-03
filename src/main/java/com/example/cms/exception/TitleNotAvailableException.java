package com.example.cms.exception;

public class TitleNotAvailableException extends RuntimeException {
	
	private String message;

	@Override
	public String getMessage() {
		return message;
	}

	public TitleNotAvailableException(String message) {
	
		this.message = message;
	}
	
	

}
