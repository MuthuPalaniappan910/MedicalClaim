package com.claim.medicalclaim.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

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

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "policyId", nullable = false)
	private Policy policyId;

	private String diagnosis;
	@JsonFormat(pattern = "yyyy-MMM-dd")
	private LocalDate admissionDate;
	@JsonFormat(pattern = "yyyy-MMM-dd")
	private LocalDate dischargeDate;
	private Double claimAmount;

	private String hospitalName;

	private String dischargeSummary;
	private String ailment;
	private LocalDate claimRaisedDate;

}
