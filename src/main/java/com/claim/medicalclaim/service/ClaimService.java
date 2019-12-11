package com.claim.medicalclaim.service;

import java.util.Optional;

import com.claim.medicalclaim.dto.RaiseClaimRequestDto;
import com.claim.medicalclaim.dto.RaiseClaimResponseDto;
import com.claim.medicalclaim.exception.GeneralException;

public interface ClaimService {

	Optional<RaiseClaimResponseDto> raiseClaim(RaiseClaimRequestDto raiseClaimRequestDto) throws GeneralException;
}
