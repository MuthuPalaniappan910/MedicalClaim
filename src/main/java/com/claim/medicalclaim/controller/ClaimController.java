package com.claim.medicalclaim.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.claim.medicalclaim.constants.ApplicationConstants;
import com.claim.medicalclaim.dto.RaiseClaimRequestDto;
import com.claim.medicalclaim.dto.RaiseClaimResponseDto;
import com.claim.medicalclaim.exception.GeneralException;
import com.claim.medicalclaim.service.ClaimService;

@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/claims")
@RestController
public class ClaimController {
	@Autowired
	ClaimService claimService;

	@PostMapping
	public ResponseEntity<Optional<RaiseClaimResponseDto>> raiseClaim(
			@RequestBody RaiseClaimRequestDto raiseClaimRequestDto) throws GeneralException {
		Optional<RaiseClaimResponseDto> claimResponse = claimService.raiseClaim(raiseClaimRequestDto);
		claimResponse.get().setStatusCode(ApplicationConstants.RAISE_CLAIM_SUCCESS_CODE);
		claimResponse.get().setStatusMessage(ApplicationConstants.RAISE_CLAIM_SUCCESS_MESSAGE);
		return new ResponseEntity<>(claimResponse, HttpStatus.OK);
	}
}
