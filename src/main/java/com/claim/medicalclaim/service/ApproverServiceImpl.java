package com.claim.medicalclaim.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medicalclaim.constants.ApplicationConstants;
import com.claim.medicalclaim.dto.ApproverClaimListResponseDto;
import com.claim.medicalclaim.dto.ClaimListDto;
import com.claim.medicalclaim.entity.Approver;
import com.claim.medicalclaim.entity.Claim;
import com.claim.medicalclaim.entity.ClaimStatus;
import com.claim.medicalclaim.repository.ApproverRepository;
import com.claim.medicalclaim.repository.ClaimStatusRepository;
import com.claim.medicalclaim.repository.ClaimRepository;

@Service
public class ApproverServiceImpl implements ApproverService {
	@Autowired
	ApproverRepository approverRepository;

	@Autowired
	ClaimStatusRepository claimStatusRepository;

	@Autowired
	ClaimRepository claimRepository;

	@Override
	public Optional<Approver> approverLogin(String approverEmail, String approverPassword) {
		return approverRepository.findByApproverEmailAndApproverPassword(approverEmail, approverPassword);
	}

	@Override
	public List<ClaimStatus> viewClaims(Long approverId) throws Exception {
		Optional<Approver> approverResponse = approverRepository.findByApproverId(approverId);
		if (!approverResponse.isPresent()) {
			throw new Exception("");
		}
		Optional<List<ClaimStatus>> claimStatusResponse = claimStatusRepository
				.findByClaimStatusAndApproverId(ApplicationConstants.PENDING, approverResponse.get());
		if (!claimStatusResponse.isPresent()) {
			throw new Exception("");
		}
		return claimStatusResponse.get();
	}

	@Override
	public ApproverClaimListResponseDto getClaimList(List<ClaimStatus> claimStatusDetailsList) {
		List<ClaimListDto> claimList = new ArrayList<>();

		claimStatusDetailsList.forEach(claimStatus -> {
			ClaimListDto claimListDto = new ClaimListDto();
			Optional<Claim> claimResponse = claimRepository.findByClaimId(claimStatus.getClaimId());
			if (claimResponse.isPresent()) {
				claimListDto.setClaimAmount(claimResponse.get().getClaimAmount());
				claimListDto.setClaimId(claimResponse.get().getClaimId());
				claimListDto.setClaimraisedDate(claimResponse.get().getClaimRaisedDate());
				claimList.add(claimListDto);
			}
		});

		ApproverClaimListResponseDto approverClaimListResponseDto = new ApproverClaimListResponseDto();
		approverClaimListResponseDto.setClaimListDto(claimList);
		return approverClaimListResponseDto;
	}

}
