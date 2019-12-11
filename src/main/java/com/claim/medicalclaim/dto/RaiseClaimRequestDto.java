package com.claim.medicalclaim.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RaiseClaimRequestDto {
	
	private String name;
	private Integer policyId;
	private String diagnosis;
	private LocalDate admissionDate;
	private LocalDate dischargeDate;
	private Double claimAmount;
	private String hospitalName;
	private String dischargeSummary;
	private String ailment;
	private LocalDate claimRaisedDate;

}
