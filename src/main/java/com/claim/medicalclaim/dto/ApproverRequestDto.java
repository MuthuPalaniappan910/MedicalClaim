package com.claim.medicalclaim.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApproverRequestDto {
	private String approverEmail;
	private String approverPassword;
}
