package com.claim.medicalclaim.service;

import java.util.Optional;

import com.claim.medicalclaim.dto.ClaimActionRequestDto;
import com.claim.medicalclaim.dto.ClaimActionResponseDto;
import com.claim.medicalclaim.entity.Approver;
import com.claim.medicalclaim.entity.ClaimStatus;
import com.claim.medicalclaim.exception.GeneralException;

public interface ApproverService {

	Optional<Approver> approverLogin(String approverEmail, String approverPassword);

	Optional<ClaimStatus> getClaimDetails(Long claimId);
	public Optional<ClaimActionResponseDto> claimAction(ClaimActionRequestDto claimActionRequestDto) throws GeneralException;

}
