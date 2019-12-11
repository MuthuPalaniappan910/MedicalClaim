package com.claim.medicalclaim.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medicalclaim.constants.ApplicationConstants;
import com.claim.medicalclaim.dto.ApproverClaimListResponseDto;
import com.claim.medicalclaim.dto.ClaimActionRequestDto;
import com.claim.medicalclaim.dto.ClaimActionResponseDto;
import com.claim.medicalclaim.dto.ClaimListDto;
import com.claim.medicalclaim.entity.Approver;
import com.claim.medicalclaim.entity.Claim;
import com.claim.medicalclaim.entity.ClaimStatus;
import com.claim.medicalclaim.exception.ApproverInvalidException;
import com.claim.medicalclaim.exception.ClaimInvalidException;
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
		return approverRepository.findByApproverEmailAndApproverPassword(approverEmail, approverPassword);
	}

	@Override
	public List<ClaimStatus> viewClaims(Long approverId) throws ClaimInvalidException, ApproverInvalidException {
		Optional<Approver> approverResponse = approverRepository.findByApproverId(approverId);
		if (!approverResponse.isPresent()) {
			throw new ApproverInvalidException(ApplicationConstants.APPROVER_INVALID);
		}
		Optional<List<ClaimStatus>> claimStatusResponse = claimStatusRepository
				.findByStatusAndApproverId(ApplicationConstants.PENDING, approverResponse.get());
		if (!claimStatusResponse.isPresent()) {
			throw new ClaimInvalidException(ApplicationConstants.CLAIM_INVALID);
		}
		return claimStatusResponse.get();
	}

	@Override
	public ApproverClaimListResponseDto getClaimList(List<ClaimStatus> claimStatusDetailsList) {
		List<ClaimListDto> claimList = new ArrayList<>();

		claimStatusDetailsList.forEach(claimStatus -> {
			ClaimListDto claimListDto = new ClaimListDto();
			Optional<Claim> claimResponse = claimRepository.findByClaimId(claimStatus.getClaimId().getClaimId());
			if (claimResponse.isPresent()) {
				claimListDto.setClaimAmount(claimResponse.get().getClaimAmount());
				claimListDto.setClaimId(claimResponse.get().getClaimId());
				claimListDto.setClaimraisedDate(claimResponse.get().getClaimRaisedDate());
				claimListDto.setAilment(claimResponse.get().getAilment());
				claimList.add(claimListDto);
			}
		});

		ApproverClaimListResponseDto approverClaimListResponseDto = new ApproverClaimListResponseDto();
		approverClaimListResponseDto.setClaimListDto(claimList);
		return approverClaimListResponseDto;
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
			claimStatusResponse.get().setStatus(ApplicationConstants.APPROVED);
			claimStatusResponse.get().setComments(claimActionRequestDto.getComments());
			claimStatusResponse.get().setAmountSanctioned(claimResponse.get().getClaimAmount());
			claimStatusRepository.save(claimStatusResponse.get());
		}
		else if(claimActionRequestDto.getActionType().equalsIgnoreCase(ApplicationConstants.ACTION_TYPE_REJECT)) {
			claimStatusResponse.get().setStatus(ApplicationConstants.REJECTED);
			claimStatusResponse.get().setComments(claimActionRequestDto.getComments());
			claimStatusRepository.save(claimStatusResponse.get());
		}
		else if(claimActionRequestDto.getActionType().equalsIgnoreCase(ApplicationConstants.ACTION_TYPE_ASSIGN)) {
			
			Optional<Approver> assigneeResponse=approverRepository.findById(claimActionRequestDto.getAssigneeId());
			if(!assigneeResponse.isPresent()) {
				throw new GeneralException("Invalid next level Approver");
			}
			claimStatusResponse.get().setStatus(ApplicationConstants.ASSIGNED);
			claimStatusResponse.get().setComments(claimActionRequestDto.getComments());
			
			ClaimStatus assigneeClaimStatus= new ClaimStatus();
			assigneeClaimStatus.setApproverId(assigneeResponse.get());
			assigneeClaimStatus.setClaimId(claimResponse.get());
			assigneeClaimStatus.setStatus(ApplicationConstants.PENDING);
			claimStatusRepository.save(assigneeClaimStatus);
			
		}
		ClaimActionResponseDto claimActionResponseDto= new ClaimActionResponseDto();
		return Optional.of(claimActionResponseDto);
	}

}
