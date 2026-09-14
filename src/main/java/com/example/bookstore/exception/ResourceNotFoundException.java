package com.example.bookstore.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = -1276262750637606693L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
