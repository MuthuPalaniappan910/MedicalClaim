package com.claim.medicalclaim.service;


import java.util.Optional;

import com.claim.medicalclaim.dto.ClaimSearchResponseDto;
import com.claim.medicalclaim.exception.PolicyClaimNotFoundException;

import com.claim.medicalclaim.dto.RaiseClaimRequestDto;
import com.claim.medicalclaim.dto.RaiseClaimResponseDto;
import com.claim.medicalclaim.exception.GeneralException;

public interface ClaimService {

	Optional<RaiseClaimResponseDto> raiseClaim(RaiseClaimRequestDto raiseClaimRequestDto) throws GeneralException;
	ClaimSearchResponseDto searchClaim(Long claimId) throws PolicyClaimNotFoundException;

}
