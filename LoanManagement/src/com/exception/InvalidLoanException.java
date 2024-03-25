package com.exception;

public class InvalidLoanException extends Exception{
	private static final long serialVersionUID = 1L;
	String message;
	
	public InvalidLoanException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
