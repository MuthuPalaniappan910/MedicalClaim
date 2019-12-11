package com.claim.medicalclaim.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClaimInvalidException extends Exception {
	private static final long serialVersionUID = 1L;

	public ClaimInvalidException(String exception) {
		super(exception);
	}
}


	
	
