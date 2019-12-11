package com.claim.medicalclaim.dto;

import java.util.List;

import com.claim.medicalclaim.entity.Hospital;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HospitalResponseDto {
	
	List<Hospital> hospitals;
	private String message;
	private Integer statusCode;

}
