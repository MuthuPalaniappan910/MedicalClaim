package com.claim.medicalclaim.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.claim.medicalclaim.constants.ApplicationConstants;
import com.claim.medicalclaim.dto.RaiseClaimRequestDto;
import com.claim.medicalclaim.dto.RaiseClaimResponseDto;
import com.claim.medicalclaim.entity.Approver;
import com.claim.medicalclaim.entity.Claim;
import com.claim.medicalclaim.entity.ClaimStatus;
import com.claim.medicalclaim.entity.Policy;
import com.claim.medicalclaim.exception.GeneralException;
import com.claim.medicalclaim.repository.ApproverRepository;
import com.claim.medicalclaim.repository.ClaimRepository;
import com.claim.medicalclaim.repository.ClaimStatusRepository;
import com.claim.medicalclaim.repository.PolicyRepository;

@Service
public class ClaimServiceImpl implements ClaimService {
	@Autowired
	ClaimRepository claimRepository;

	@Autowired
	PolicyRepository policyRepository;
	
	@Autowired
	ClaimStatusRepository claimStatusRepository;
	
	@Autowired
	ApproverRepository approverRepository;

	
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
		claimStatus.setStatus(ApplicationConstants.PENDING);
		claimStatusRepository.save(claimStatus);
		
		
		RaiseClaimResponseDto raiseClaimResponseDto= new RaiseClaimResponseDto();
		return Optional.of(raiseClaimResponseDto);
	}

}
