package com.example.bookstore.exception;

public class DuplicateResourceException extends RuntimeException {

	private static final long serialVersionUID = 5029382482176173475L;

	public DuplicateResourceException(String message) {
		super(message);
	}
}
