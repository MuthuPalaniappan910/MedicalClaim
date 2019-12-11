package com.claim.medicalclaim.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClaimActionRequestDto {

	private Long claimId;
	private String comments;
	private String actionType;
	private Long approverId;
	private Long assigneeId;
}
