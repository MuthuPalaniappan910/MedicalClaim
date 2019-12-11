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
@Table(name = "hospital")

@Getter
@Setter
@SequenceGenerator(name = "hospitalsequence", initialValue = 1, allocationSize = 1)
public class Hospital {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "hospitalsequence")
	private Integer hospitalId;
	private String hospitalName;
	private String hospitalLocation;

}
