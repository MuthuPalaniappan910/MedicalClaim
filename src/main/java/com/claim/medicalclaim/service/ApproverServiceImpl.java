package com.claim.medicalclaim.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medicalclaim.constants.ApplicationConstants;
import com.claim.medicalclaim.dto.ClaimActionRequestDto;
import com.claim.medicalclaim.dto.ClaimActionResponseDto;
import com.claim.medicalclaim.entity.Approver;
import com.claim.medicalclaim.entity.Claim;
import com.claim.medicalclaim.entity.ClaimStatus;
import com.claim.medicalclaim.exception.GeneralException;
import com.claim.medicalclaim.repository.ApproverRepository;
import com.claim.medicalclaim.repository.ClaimRepository;
import com.claim.medicalclaim.repository.ClaimStatusRepository;

@Service
public class ApproverServiceImpl implements ApproverService {
	@Autowired
	ApproverRepository approverRepository;
	
	@Autowired
	ClaimRepository claimRepository;
	
	@Autowired
	ClaimStatusRepository claimStatusRepository;

	@Override
	public Optional<Approver> approverLogin(String approverEmail, String approverPassword) {
		return approverRepository.findByApproverEmailAndApproverPassword(approverEmail,approverPassword);
	}

	@Override
	public Optional<ClaimStatus> getClaimDetails(Long claimId) {
		
		return null;
	}
	
	@Transactional
	public Optional<ClaimActionResponseDto> claimAction(ClaimActionRequestDto claimActionRequestDto) throws GeneralException{
		Optional<Claim> claimResponse= claimRepository.findByClaimId(claimActionRequestDto.getClaimId());
		if(!claimResponse.isPresent()) {
			throw new GeneralException("Invalid ClaimId");
		}
		Optional<ClaimStatus> claimStatusResponse= claimStatusRepository.findByClaimId(claimResponse.get());
		
		//ClaimStatus claimStatus= new ClaimStatus();
		if(claimActionRequestDto.getActionType().equalsIgnoreCase(ApplicationConstants.ACTION_TYPE_APPROVE)) {
			claimStatusResponse.get().setClaimStatus(ApplicationConstants.APPROVED);
			claimStatusResponse.get().setComments(claimActionRequestDto.getComments());
			claimStatusResponse.get().setAmountSanctioned(claimResponse.get().getClaimAmount());
			claimStatusRepository.save(claimStatusResponse.get());
		}
		else if(claimActionRequestDto.getActionType().equalsIgnoreCase(ApplicationConstants.ACTION_TYPE_REJECT)) {
			claimStatusResponse.get().setClaimStatus(ApplicationConstants.REJECTED);
			claimStatusResponse.get().setComments(claimActionRequestDto.getComments());
			claimStatusRepository.save(claimStatusResponse.get());
		}
		else if(claimActionRequestDto.getActionType().equalsIgnoreCase(ApplicationConstants.ACTION_TYPE_ASSIGN)) {
			
			Optional<Approver> assigneeResponse=approverRepository.findById(claimActionRequestDto.getAssigneeId());
			if(!assigneeResponse.isPresent()) {
				throw new GeneralException("Invalid next level Approver");
			}
			claimStatusResponse.get().setClaimStatus(ApplicationConstants.ASSIGNED);
			claimStatusResponse.get().setComments(claimActionRequestDto.getComments());
			
			ClaimStatus assigneeClaimStatus= new ClaimStatus();
			assigneeClaimStatus.setApproverId(assigneeResponse.get());
			assigneeClaimStatus.setClaimId(claimResponse.get());
			assigneeClaimStatus.setClaimStatus(ApplicationConstants.PENDING);
			claimStatusRepository.save(assigneeClaimStatus);
			
		}
		ClaimActionResponseDto claimActionResponseDto= new ClaimActionResponseDto();
		return Optional.of(claimActionResponseDto);
	}
	
	

}
