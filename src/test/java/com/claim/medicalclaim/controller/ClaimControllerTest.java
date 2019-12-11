package com.claim.medicalclaim.controller;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.claim.medicalclaim.constants.ApplicationConstants;
import com.claim.medicalclaim.dto.RaiseClaimRequestDto;
import com.claim.medicalclaim.dto.RaiseClaimResponseDto;
import com.claim.medicalclaim.service.ClaimService;

@RunWith(MockitoJUnitRunner.class)
public class ClaimControllerTest {
	@Mock
	ClaimService claimService;
	@InjectMocks
	ClaimController claimController;
	RaiseClaimResponseDto raiseClaimResponseDto = null;
	RaiseClaimRequestDto raiseClaimRequestDto = null;
	RaiseClaimRequestDto NewraiseClaimRequestDto = null;

	@Before
	public void before() {
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
		raiseClaimRequestDto.setClaimRaisedDate(LocalDate.now());
		raiseClaimResponseDto = new RaiseClaimResponseDto();
		raiseClaimResponseDto.setStatusCode(ApplicationConstants.RAISE_CLAIM_SUCCESS_CODE);
		raiseClaimResponseDto.setStatusMessage(ApplicationConstants.RAISE_CLAIM_SUCCESS_MESSAGE);
	}

	@Test
	public void raiseClaim() throws Exception {
		Mockito.when(claimService.raiseClaim(raiseClaimRequestDto)).thenReturn(Optional.of(raiseClaimResponseDto));
		Integer statusCode = claimController.raiseClaim(raiseClaimRequestDto).getStatusCodeValue();
		assertEquals(ApplicationConstants.RAISE_CLAIM_SUCCESS_CODE, statusCode);
	}

}
