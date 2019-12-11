package com.claim.medicalclaim.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medicalclaim.constants.ApplicationConstants;
import com.claim.medicalclaim.dto.ClaimSearchResponseDto;
import com.claim.medicalclaim.entity.Claim;
import com.claim.medicalclaim.entity.ClaimStatus;
import com.claim.medicalclaim.exception.PolicyClaimNotFoundException;
import com.claim.medicalclaim.repository.ClaimRepository;
import com.claim.medicalclaim.repository.ClaimStatusRepository;

@Service
public class ClaimServiceImpl implements ClaimService {

	@Autowired
	ClaimStatusRepository claimStatusRepository;
	
	@Autowired
	ClaimRepository claimRepository;

	@Override
	public ClaimSearchResponseDto searchClaim(Long claimId) throws PolicyClaimNotFoundException {
		Optional<Claim> claim=claimRepository.findByClaimId(claimId);
		Optional<ClaimStatus> claimStatus = claimStatusRepository.findByClaimId(claim.get());
		if(!claim.isPresent()) {
			throw new PolicyClaimNotFoundException(ApplicationConstants.POLICY_CLAIM_NOT_FOUND);
		}
		if (claimStatus.isPresent()) {
			ClaimSearchResponseDto claimSearchResponseDto = new ClaimSearchResponseDto();
			ClaimStatus claimStatusResponse=claimStatus.get();
			claimSearchResponseDto.setAmountSanctioned(claimStatusResponse.getAmountSanctioned());
			claimSearchResponseDto.setClaimId(claim.get().getClaimId());
			claimSearchResponseDto.setClaimStatus(claimStatusResponse.getClaimStatus());
			claimSearchResponseDto.setComments(claimStatusResponse.getComments());
			return claimSearchResponseDto;

		} else {
			throw new PolicyClaimNotFoundException(ApplicationConstants.POLICY_CLAIM_NOT_FOUND);
		}

	}

}
