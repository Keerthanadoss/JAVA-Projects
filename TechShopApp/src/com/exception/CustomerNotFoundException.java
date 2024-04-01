package com.exception;

public class CustomerNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	String message;
	
	public CustomerNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
