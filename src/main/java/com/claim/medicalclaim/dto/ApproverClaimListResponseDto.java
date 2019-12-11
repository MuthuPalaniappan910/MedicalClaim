package com.claim.medicalclaim.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApproverClaimListResponseDto {
	private List<ClaimListDto> claimListDto;
	private Integer statusCode;
	private String statusMessage;
}
