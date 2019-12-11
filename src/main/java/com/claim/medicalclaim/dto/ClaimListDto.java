package com.claim.medicalclaim.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClaimListDto {
	private Long claimId;
	private LocalDate claimraisedDate;
	private Double claimAmount;
}
