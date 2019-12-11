package com.claim.medicalcliam.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "claim")

@Getter
@Setter
@SequenceGenerator(name = "claimsequence", initialValue = 1000000, allocationSize = 1)
public class Claim {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "claimsequence")
	private Long claimId;
	private String name;
	private Integer policyId;
	private String diagnosis;
	private LocalDate admissionDate;
	private LocalDate dischargeDate;
	private Double claimAmount;
	private Integer hospitalId;
	private String dischargeSummary;
	private String ailment;
	private LocalDate claiRaisedDate;
}
