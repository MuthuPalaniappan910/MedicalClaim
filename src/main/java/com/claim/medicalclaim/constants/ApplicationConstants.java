package com.claim.medicalclaim.constants;

import org.springframework.http.HttpStatus;

public class ApplicationConstants {

	private ApplicationConstants() {

	}

	public static final Integer RAISE_CLAIM_SUCCESS_CODE = 200;
	public static final String RAISE_CLAIM_SUCCESS_MESSAGE = "your claim has been successfully registered";

	public static final String SUCCESS = "Success";
	public static final String FAILURE = "Failure";

	public static final String POLICY_CLAIM_NOT_FOUND = "Policy claim not found";

	public static final String PENDING = "Pending";
	public static final Integer SUCCESS_STATUS_CODE = HttpStatus.OK.value();
	public static final Integer FAILURE_STATUS_CODE = HttpStatus.NOT_FOUND.value();
	public static final String APPROVER_ERROR_MESSAGE = "Sorry!!you are not a valid approver";

	public static final String ERROR_MESSAGE = "Sorry!!Please become a registered user";
	public static final Integer ERROR_CODE = 404;
	public static final String SUCCESS_MESSAGE = "You are a valid approver";
	public static final Integer SUCCESS_CODE = 200;

	
	public static final String ACTION_TYPE_APPROVE = "Approve";
	public static final String ACTION_TYPE_REJECT = "Reject";
	public static final String ACTION_TYPE_ASSIGN = "Assign";

	
	public static final String APPROVED = "Approved";
	public static final String REJECTED = "Rejected";
	public static final String ASSIGNED = "Assigned";
	
	public static final String EMPTY_VIEW_LIST = "There are currently no claims for you to view";
	public static final String DISPLAY_VIEW_LIST = "Claims are being displayed";

	public static final String APPROVER_INVALID="Invalid Approver";
	public static final String CLAIM_INVALID="Invalid Claim Query";
	
}
