package com.example.cms.exception;

public class PanelNotFoundByIdException extends RuntimeException {

	public String message;

	public PanelNotFoundByIdException(String message) {
		super();
		this.message = message;
	}
	
	public String getMessage() {
		return message;
		
	}
}
