package com.claim.medicalclaim.exception;

import org.springframework.stereotype.Component;

@Component
public class ErrorResponse {
	String message;

	public ErrorResponse() {

	}

	public ErrorResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
