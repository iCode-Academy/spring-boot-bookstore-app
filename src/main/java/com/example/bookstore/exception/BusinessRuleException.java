package com.example.bookstore.exception;

public class BusinessRuleException extends RuntimeException {

	private static final long serialVersionUID = 700005118917895348L;

	public BusinessRuleException(String message) {
		super(message);
	}

}
