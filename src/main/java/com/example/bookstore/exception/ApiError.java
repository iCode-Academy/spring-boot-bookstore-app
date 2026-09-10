package com.example.bookstore.exception;

import java.time.LocalDateTime;
import java.util.Map;

/**
		{
		  "timestamp": "2026-09-10T15:45:00",
		  "status": 409,
		  "error": "Conflict",
		  "message": "Cart is empty",
		  "fieldErrors": {}
		}
 */
public record ApiError(
		LocalDateTime timestamp,

		int status,

		String error, String message,

		Map<String, String> fieldErrors) {
}
