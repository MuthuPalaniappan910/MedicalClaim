package com.claim.medicalclaim.constants;

import org.springframework.http.HttpStatus;

public class ApplicationConstants {
	
	public static final Integer SUCCESS_STATUS_CODE = HttpStatus.OK.value();
	public static final Integer FAILURE_STATUS_CODE = HttpStatus.NOT_FOUND.value();
	public static final String SUCCESS = "Success";
	public static final String FAILURE = "Failure";
	public static final String ERROR_MESSAGE = "Sorry!!Please become a registered user";
	public static final Integer ERROR_CODE = 404;
	public static final String SUCCESS_MESSAGE = "You are a valid user";
	public static final Integer SUCCESS_CODE = 200;

}
