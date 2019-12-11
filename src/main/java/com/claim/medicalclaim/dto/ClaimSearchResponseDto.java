package com.claim.medicalclaim.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ClaimSearchResponseDto {
	
	private String message;
	private Integer statusCode;
	private String comments;
	private Long claimId;
	private Double amountSanctioned;
	private String claimStatus;

}
