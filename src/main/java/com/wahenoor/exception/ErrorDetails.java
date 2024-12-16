package com.wahenoor.exception;

import java.time.LocalDateTime;

public class ErrorDetails {

	private String message;
	private String details;
	private LocalDateTime timestamp;
	private String errorCode;

	public ErrorDetails() {
	}

	public ErrorDetails(String message, String details, LocalDateTime timestamp, String errorCode) {
		this.message = message;
		this.details = details;
		this.timestamp = timestamp;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
