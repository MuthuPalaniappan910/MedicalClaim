package com.claim.medicalclaim.service;


import java.time.LocalDate;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medicalclaim.constants.ApplicationConstants;
import com.claim.medicalclaim.dto.ClaimSearchResponseDto;
import com.claim.medicalclaim.dto.RaiseClaimRequestDto;
import com.claim.medicalclaim.dto.RaiseClaimResponseDto;
import com.claim.medicalclaim.entity.Approver;
import com.claim.medicalclaim.entity.Claim;
import com.claim.medicalclaim.entity.ClaimStatus;
import com.claim.medicalclaim.entity.Policy;
import com.claim.medicalclaim.exception.GeneralException;
import com.claim.medicalclaim.exception.PolicyClaimNotFoundException;
import com.claim.medicalclaim.repository.ApproverRepository;
import com.claim.medicalclaim.repository.ClaimRepository;
import com.claim.medicalclaim.repository.ClaimStatusRepository;
import com.claim.medicalclaim.repository.PolicyRepository;

@Service
public class ClaimServiceImpl implements ClaimService {

	@Autowired
	ClaimStatusRepository claimStatusRepository;
	
	@Autowired
	ClaimRepository claimRepository;
	

	@Autowired
	PolicyRepository policyRepository;
	
	
	@Autowired
	ApproverRepository approverRepository;

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
	
	@Transactional
	public Optional<RaiseClaimResponseDto> raiseClaim(RaiseClaimRequestDto raiseClaimRequestDto) throws GeneralException {
	
		if(raiseClaimRequestDto.getAdmissionDate().isAfter(raiseClaimRequestDto.getDischargeDate())) {
			throw new GeneralException("Check the discharge date");
		}
		Claim claim= new Claim();
		BeanUtils.copyProperties(raiseClaimRequestDto, claim);
		Optional<Policy> policyResponse=policyRepository.findById(raiseClaimRequestDto.getPolicyId());
		if(!policyResponse.isPresent()) {
			throw new GeneralException("Invalid Policy Id");
		}
		claim.setPolicyId(policyResponse.get());
		claim.setClaimRaisedDate(LocalDate.now());
		claim=claimRepository.save(claim);
		
		ClaimStatus claimStatus= new ClaimStatus();
		claimStatus.setClaimId(claim);
		Optional<Approver> approverResponse=  approverRepository.findByAilment(raiseClaimRequestDto.getAilment());
		
		if(!approverResponse.isPresent()) {
			throw new GeneralException("No Approvers Available for entered ailment");
		}
		claimStatus.setApproverId(approverResponse.get());
		claimStatus.setClaimStatus(ApplicationConstants.PENDING);
		claimStatusRepository.save(claimStatus);
		
		
		RaiseClaimResponseDto RaiseClaimResponseDto= new RaiseClaimResponseDto();
		return Optional.of(RaiseClaimResponseDto);

	}




	
	
}
