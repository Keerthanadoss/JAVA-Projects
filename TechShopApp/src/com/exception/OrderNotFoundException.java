package com.exception;

public class OrderNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	String message;
	
	public OrderNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
