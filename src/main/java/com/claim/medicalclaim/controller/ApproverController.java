package com.claim.medicalclaim.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.medicalclaim.constants.ApplicationConstants;
import com.claim.medicalclaim.dto.ApproverClaimListResponseDto;
import com.claim.medicalclaim.dto.ApproverRequestDto;
import com.claim.medicalclaim.dto.ApproverResponseDto;
import com.claim.medicalclaim.entity.Approver;
import com.claim.medicalclaim.entity.ClaimStatus;
import com.claim.medicalclaim.exception.GeneralException;
import com.claim.medicalclaim.service.ApproverService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
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
		String approverPassword = approverRequestDto.getApproverPassword();
		Optional<Approver> approver = approverService.approverLogin(approverEmail, approverPassword);
		if (approver.isPresent()) {
			approverResponseDto.setStatusCode(ApplicationConstants.SUCCESS_CODE);
			approverResponseDto.setMessage(ApplicationConstants.SUCCESS_MESSAGE);
			approverResponseDto.setApproverId(approver.get().getApproverId());
			approverResponseDto.setApproverRole(approver.get().getApproverRole());
			return new ResponseEntity<>(approverResponseDto, HttpStatus.OK);
		}
		approverResponseDto.setStatusCode(ApplicationConstants.ERROR_CODE);
		approverResponseDto.setMessage(ApplicationConstants.APPROVER_ERROR_MESSAGE);
		return new ResponseEntity<>(approverResponseDto, HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{approverId}")
	public ResponseEntity<ApproverClaimListResponseDto> viewClaims(@PathVariable("approverId") Long approverId)
			throws GeneralException {
		ApproverClaimListResponseDto approverClaimListResponseDto = new ApproverClaimListResponseDto();
		List<ClaimStatus> claimStatusDetails = approverService.viewClaims(approverId);
		if (claimStatusDetails.isEmpty()) {
			log.info("No claims found");
			approverClaimListResponseDto.setStatusCode(ApplicationConstants.ERROR_CODE);
			approverClaimListResponseDto.setStatusMessage(ApplicationConstants.EMPTY_VIEW_LIST);
			return new ResponseEntity<>(approverClaimListResponseDto, HttpStatus.NOT_FOUND);
		}
		log.info("Displaying claim details");
		approverClaimListResponseDto = approverService.getClaimList(claimStatusDetails);
		approverClaimListResponseDto.setStatusCode(ApplicationConstants.SUCCESS_CODE);
		approverClaimListResponseDto.setStatusMessage(ApplicationConstants.DISPLAY_VIEW_LIST);
		return new ResponseEntity<>(approverClaimListResponseDto, HttpStatus.OK);
	}
}
