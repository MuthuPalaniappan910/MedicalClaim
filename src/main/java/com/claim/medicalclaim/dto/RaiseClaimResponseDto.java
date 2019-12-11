package com.claim.medicalclaim.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RaiseClaimResponseDto {
	private Long claimId;
	private Integer statusCode;
	private String statusMessage;
}
