package com.claim.medicalclaim.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.medicalclaim.constants.ApplicationConstants;
import com.claim.medicalclaim.dto.ApproverRequestDto;
import com.claim.medicalclaim.dto.ApproverResponseDto;
import com.claim.medicalclaim.entity.Approver;
import com.claim.medicalclaim.service.ApproverService;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/approvers/claims")
@Slf4j
@RestController
public class ApproverController {
	@Autowired
	ApproverService approverService;

	@PostMapping("/login")
	public ResponseEntity<ApproverResponseDto> approverLogin(@RequestBody ApproverRequestDto approverRequestDto) {
		log.info("Inside login method");
		ApproverResponseDto approverResponseDto = new ApproverResponseDto();
		String approverEmail = approverRequestDto.getApproverEmail();
		String approverPassword = approverRequestDto.getApproverPassowrd();
		Optional<Approver> approver = approverService.approverLogin(approverEmail, approverPassword);
		if (approver.isPresent()) {
			approverResponseDto.setStatusCode(ApplicationConstants.SUCCESS_CODE);
			approverResponseDto.setStatusMessage(ApplicationConstants.SUCCESS_MESSAGE);
			return new ResponseEntity<>(approverResponseDto, HttpStatus.OK);
		}
		approverResponseDto.setStatusCode(ApplicationConstants.ERROR_CODE);
		approverResponseDto.setStatusMessage(ApplicationConstants.ERROR_MESSAGE);
		return new ResponseEntity<>(approverResponseDto, HttpStatus.NOT_FOUND);
	}
}
