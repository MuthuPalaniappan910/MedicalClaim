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
import com.claim.medicalclaim.dto.RaiseClaimRequestDto;
import com.claim.medicalclaim.dto.RaiseClaimResponseDto;
import com.claim.medicalclaim.exception.GeneralException;
import com.claim.medicalclaim.service.ClaimService;

@RequestMapping("/claims")
@RestController
public class ClaimController {
	@Autowired
	ClaimService claimService;

	@PostMapping
	ResponseEntity<Optional<RaiseClaimResponseDto>> raiseClaim(@RequestBody RaiseClaimRequestDto raiseClaimRequestDto)
			throws Exception {
		Optional<RaiseClaimResponseDto> claimResponse = claimService.raiseClaim(raiseClaimRequestDto);
		if (!claimResponse.isPresent()) {
			throw new GeneralException("Unable to raise claim");
		}
		claimResponse.get().setStatusCode(ApplicationConstants.RAISE_CLAIM_SUCCESS_CODE);
		claimResponse.get().setStatusMessage(ApplicationConstants.RAISE_CLAIM_SUCCESS__MESSAGE);
		return new ResponseEntity<>(claimResponse, HttpStatus.OK);
	}
}
