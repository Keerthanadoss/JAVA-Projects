package com.exception;

public class OutOfStock extends Exception{
	private static final long serialVersionUID = 1L;
	String message;
	
	public OutOfStock(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
