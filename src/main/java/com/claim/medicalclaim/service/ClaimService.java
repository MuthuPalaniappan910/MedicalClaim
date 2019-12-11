package com.claim.medicalclaim.service;

import com.claim.medicalclaim.dto.ClaimSearchResponseDto;
import com.claim.medicalclaim.exception.PolicyClaimNotFoundException;

public interface ClaimService {

	ClaimSearchResponseDto searchClaim(Long claimId) throws PolicyClaimNotFoundException;
}
