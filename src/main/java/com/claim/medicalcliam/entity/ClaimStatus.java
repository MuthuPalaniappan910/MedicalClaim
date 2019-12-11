package com.claim.medicalclaim.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "claimstatus")

@Getter
@Setter
public class ClaimStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long claimStatusID;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "claimId", nullable = false)
	private Claim claimId;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "approverId", nullable = false)
	private Approver approverId;

	private Double amountSanctioned;
	private String claimStatus;
	private String comments;
}
