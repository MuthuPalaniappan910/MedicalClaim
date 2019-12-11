package com.claim.medicalclaim.exception;

import java.time.LocalDate;

public class ErrorResponse {
	String exceptionMessage;
	LocalDate date;
	public ErrorResponse() {
		
	}
	public ErrorResponse(String exceptionMessage,LocalDate date) {
		super();
		this.exceptionMessage=exceptionMessage;
		this.date=date;
		
	}

}
