package com.claim.medicalclaim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.medicalclaim.constants.ApplicationConstants;
import com.claim.medicalclaim.dto.HospitalResponseDto;
import com.claim.medicalclaim.entity.Hospital;
import com.claim.medicalclaim.service.HospitalService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/hospitals")
@Slf4j
public class HospitalController {
	
	@Autowired
	HospitalService hospitalService;
	
	@GetMapping("")
	public ResponseEntity<HospitalResponseDto> getHospitals() {
		log.info("Entering the hospital service method");
		List<Hospital> hospitals = hospitalService.findAllHospitals();
		HospitalResponseDto hospitalResponseDto=new HospitalResponseDto();
		if (hospitals.isEmpty()) {
			hospitalResponseDto.setMessage(ApplicationConstants.FAILURE);
			hospitalResponseDto.setStatusCode(ApplicationConstants.FAILURE_STATUS_CODE);
			return new ResponseEntity<>(hospitalResponseDto,HttpStatus.NOT_FOUND);
		}
		hospitalResponseDto.setHospitals(hospitals);
		hospitalResponseDto.setMessage(ApplicationConstants.SUCCESS);
		hospitalResponseDto.setStatusCode(ApplicationConstants.SUCCESS_STATUS_CODE);
		return new ResponseEntity<>(hospitalResponseDto, HttpStatus.OK);	
	}

}





	



