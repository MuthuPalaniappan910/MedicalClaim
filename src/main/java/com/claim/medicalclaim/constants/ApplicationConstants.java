package com.claim.medicalclaim.constants;

import org.springframework.http.HttpStatus;

public class ApplicationConstants {
	
	public static final Integer SUCCESS_STATUS_CODE = HttpStatus.OK.value();
	public static final Integer FAILURE_STATUS_CODE = HttpStatus.NOT_FOUND.value();
	public static final String SUCCESS = "Success";
	public static final String FAILURE = "Failure";
	public static final String POLICY_CLAIM_NOT_FOUND = "Policy claim not found";

}
