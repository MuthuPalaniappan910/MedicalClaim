package com.claim.medicalcliam.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "approver")

@Getter
@Setter
@SequenceGenerator(name = "approversequence", initialValue = 10000, allocationSize = 1)
public class Approver {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "approversequence")
	private Long approverId;
	private String approverName;
	private String approverPassword;
	private String approverEmail;
	private String approverRole;
}
