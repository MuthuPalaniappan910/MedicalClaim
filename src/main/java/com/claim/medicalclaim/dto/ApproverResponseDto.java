package com.claim.medicalclaim.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApproverResponseDto {
	private Long approverId;
	private String approverRole;
	private Integer statusCode;
	private String message;
}
