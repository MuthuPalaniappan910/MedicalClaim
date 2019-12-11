package com.claim.medicalclaim.service;

import java.util.List;
import java.util.Optional;

import com.claim.medicalclaim.dto.ApproverClaimListResponseDto;
import com.claim.medicalclaim.entity.Approver;
import com.claim.medicalclaim.entity.ClaimStatus;
import com.claim.medicalclaim.exception.ApproverInvalidException;
import com.claim.medicalclaim.exception.ClaimInvalidException;

public interface ApproverService {

	Optional<Approver> approverLogin(String approverEmail, String approverPassword);

	List<ClaimStatus> viewClaims(Long approverId) throws ApproverInvalidException, ClaimInvalidException;

	ApproverClaimListResponseDto getClaimList(List<ClaimStatus> claimStatusDetails);

}
