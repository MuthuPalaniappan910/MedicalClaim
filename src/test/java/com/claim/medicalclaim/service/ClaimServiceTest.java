package com.claim.medicalclaim.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

@RunWith(SpringJUnit4ClassRunner.class)
public class ClaimServiceTest {

	@Mock
	ClaimStatusRepository claimStatusRepository;

	@Mock
	ApproverRepository approverRepository;

	@Mock
	ClaimRepository claimRepository;

	@Mock
	PolicyRepository policyRepository;

	@InjectMocks
	ClaimServiceImpl claimServiceImpl;

	ClaimSearchResponseDto claimSearchResponseDto = null;
	Claim claim = null;
	ClaimStatus claimStatus = null;

	RaiseClaimResponseDto raiseClaimResponseDto = null;
	RaiseClaimRequestDto raiseClaimRequestDto = null;
	RaiseClaimRequestDto NewraiseClaimRequestDto = null;
	Policy policy = null;

	@Before
	public void before() {
		claimSearchResponseDto = new ClaimSearchResponseDto();
		claimSearchResponseDto.setAmountSanctioned(1000.00);
		claimSearchResponseDto.setClaimId(1L);
		claimSearchResponseDto.setClaimStatus("Pending");
		claimSearchResponseDto.setComments("Approved");

		claim = new Claim();
		claim.setClaimAmount(100.00);
		claim.setClaimId(1L);

		claimStatus = new ClaimStatus();
		claimStatus.setStatus("pending");
		claimStatus.setStatus("pending");
		claimStatus.setClaimStatusID(1L);

		raiseClaimRequestDto = new RaiseClaimRequestDto();
		raiseClaimRequestDto.setName("satvik");
		raiseClaimRequestDto.setPolicyId(1);
		raiseClaimRequestDto.setDiagnosis("fever");
		raiseClaimRequestDto.setAdmissionDate(LocalDate.of(2019, 12, 01));
		raiseClaimRequestDto.setDischargeDate(LocalDate.of(2019, 12, 25));
		raiseClaimRequestDto.setClaimAmount(4500.600);
		raiseClaimRequestDto.setHospitalName("narayana");
		raiseClaimRequestDto.setDischargeSummary("dengue");
		raiseClaimRequestDto.setAilment("ache");
		raiseClaimResponseDto = new RaiseClaimResponseDto();
		raiseClaimResponseDto.setStatusCode(ApplicationConstants.RAISE_CLAIM_SUCCESS_CODE);
		raiseClaimResponseDto.setStatusMessage(ApplicationConstants.RAISE_CLAIM_SUCCESS_MESSAGE);

		policy = new Policy();
		policy.setPolicyId(1);
		policy.setPolicyStartDate(LocalDate.of(2018, 12, 01));
		policy.setPolicyEndDate(LocalDate.of(2020, 12, 01));
	}

	@Test
	public void testsearchClaimForPositive() throws PolicyClaimNotFoundException {
		Mockito.when(claimRepository.findByClaimId(1L)).thenReturn(Optional.of(claim));
		Mockito.when(claimStatusRepository.findByClaimId(claim)).thenReturn(Optional.of(claimStatus));
		ClaimSearchResponseDto response = claimServiceImpl.searchClaim(1L);
		assertEquals(claimSearchResponseDto.getClaimId(), response.getClaimId());
	}

	@Test(expected = PolicyClaimNotFoundException.class)
	public void testsearchClaimForNegative() throws PolicyClaimNotFoundException {
		Optional<Claim> claim1 = Optional.ofNullable(null);
		Mockito.when(claimRepository.findByClaimId(1L)).thenReturn(claim1);
		claimServiceImpl.searchClaim(1L);
	}

	@Test(expected = PolicyClaimNotFoundException.class)
	public void testsearchClaimForNull() throws PolicyClaimNotFoundException {
		Optional<ClaimStatus> claim1 = Optional.ofNullable(null);
		Mockito.when(claimRepository.findByClaimId(1L)).thenReturn(Optional.of(claim));
		Mockito.when(claimStatusRepository.findByClaimId(claim)).thenReturn(claim1);
		claimServiceImpl.searchClaim(1L);
	}

	@Test(expected = GeneralException.class)
	public void testRaiseClaimForNegativeDate() throws GeneralException {
		RaiseClaimRequestDto raiseClaimRequestDto1 = new RaiseClaimRequestDto();
		;
		raiseClaimRequestDto1.setAdmissionDate(LocalDate.of(2019, 12, 25));
		raiseClaimRequestDto1.setDischargeDate(LocalDate.of(2019, 12, 01));
		claimServiceImpl.raiseClaim(raiseClaimRequestDto1);
	}

	@Test(expected = GeneralException.class)
	public void testRaiseClaimForNegativePolicy() throws GeneralException {
		Optional<Policy> policy = Optional.ofNullable(null);
		Mockito.when(policyRepository.findById(raiseClaimRequestDto.getPolicyId())).thenReturn(policy);
		claimServiceImpl.raiseClaim(raiseClaimRequestDto);
	}

	@Test(expected = GeneralException.class)
	public void testRaiseClaimForNegativeApprover() throws GeneralException {
		Optional<Approver> approverResponse = Optional.ofNullable(null);
		Mockito.when(policyRepository.findById(raiseClaimRequestDto.getPolicyId())).thenReturn(Optional.of(policy));
		Mockito.when(approverRepository.findByAilment(raiseClaimRequestDto.getAilment())).thenReturn(approverResponse);
		claimServiceImpl.raiseClaim(raiseClaimRequestDto);
	}

}
