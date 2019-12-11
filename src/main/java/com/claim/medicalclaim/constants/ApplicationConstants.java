package com.claim.medicalclaim.constants;

import org.springframework.http.HttpStatus;

public class ApplicationConstants {
	private ApplicationConstants() {

	}

	public static final Integer RAISE_CLAIM_SUCCESS_CODE = 200;
	public static final String RAISE_CLAIM_SUCCESS__MESSAGE = "your claim has been successfully registered";
	public static final String SUCCESS = "Success";
	public static final String FAILURE = "Failure";

	public static final String POLICY_CLAIM_NOT_FOUND = "Policy claim not found";

	public static final String PENDING = "Pending";
	public static final Integer SUCCESS_STATUS_CODE = HttpStatus.OK.value();
	public static final Integer FAILURE_STATUS_CODE = HttpStatus.NOT_FOUND.value();
	public static final String ERROR_MESSAGE = "Sorry!!Please become a registered user";
	public static final Integer ERROR_CODE = 404;
	public static final String SUCCESS_MESSAGE = "You are a valid user";
	public static final Integer SUCCESS_CODE = 200;
	
	public static final String ACTION_TYPE_APPROVE = "Approve";
	public static final String ACTION_TYPE_REJECT = "Reject";
	public static final String ACTION_TYPE_ASSIGN = "Assign";

	
	public static final String APPROVED = "Approved";
	public static final String REJECTED = "Rejected";
	public static final String ASSIGNED = "Assigned";
	

}
