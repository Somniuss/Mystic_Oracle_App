package com.somniuss.oracle.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		super("User not found");
	}

	public UserNotFoundException(String message) {
		super(message);
	}
}
