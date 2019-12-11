package com.claim.medicalclaim.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.claim.medicalclaim.constants.ApplicationConstants;
import com.claim.medicalclaim.dto.ApproverClaimListResponseDto;
import com.claim.medicalclaim.dto.ApproverRequestDto;
import com.claim.medicalclaim.dto.ApproverResponseDto;
import com.claim.medicalclaim.entity.Approver;
import com.claim.medicalclaim.entity.Claim;
import com.claim.medicalclaim.entity.ClaimStatus;
import com.claim.medicalclaim.exception.GeneralException;
import com.claim.medicalclaim.service.ApproverService;

@RunWith(SpringJUnit4ClassRunner.class)
public class ApproverControllerTest {

	@InjectMocks
	ApproverController approverController;
	@Mock
	ApproverService approverService;

	ApproverResponseDto approverResponseDto = null;
	ApproverResponseDto approverResponseDto1 = null;
	ApproverRequestDto approverRequestDto = null;
	ApproverRequestDto approverRequestDto1 = null;
	
	ApproverClaimListResponseDto approverClaimListResponseDto=null;

	ClaimStatus claimStatus = null;
	Claim claim = null;
	Approver approver = null;
	List<ClaimStatus> claimList = null;
	List<ClaimStatus> claimList1 = null;

	@Before
	public void before() {
		approverRequestDto = new ApproverRequestDto();
		approverRequestDto.setApproverEmail("muthu@gmail.com");
		approverRequestDto.setApproverPassowrd("muthu123");

		approverRequestDto1 = new ApproverRequestDto();
		approverRequestDto1.setApproverEmail("muthu4@gmail.com");
		approverRequestDto1.setApproverPassowrd("muthu123");

		approverResponseDto = new ApproverResponseDto();
		approverResponseDto.setStatusCode(ApplicationConstants.SUCCESS_CODE);
		approverResponseDto.setStatusMessage(ApplicationConstants.SUCCESS_MESSAGE);

		approverResponseDto1 = new ApproverResponseDto();
		approverResponseDto1.setStatusCode(ApplicationConstants.ERROR_CODE);
		approverResponseDto1.setStatusMessage(ApplicationConstants.ERROR_MESSAGE);

		claimStatus = new ClaimStatus();
		approver = new Approver();
		approverClaimListResponseDto=new ApproverClaimListResponseDto();
		claim = new Claim();
		claim.setClaimId(1L);
		claimList = new ArrayList<>();
		approver.setApproverId(1L);
		claimStatus.setClaimId(claim);
		claimStatus.setApproverId(approver);
		claimList.add(claimStatus);

	}

	@Test
	public void testApproverLoginPositive() {
		Mockito.when(approverService.approverLogin(approverRequestDto.getApproverEmail(),
				approverRequestDto.getApproverPassowrd())).thenReturn(Optional.of(new Approver()));
		Integer expected = approverController.approverLogin(approverRequestDto).getStatusCodeValue();
		assertEquals(ApplicationConstants.SUCCESS_CODE, expected);
	}

	@Test
	public void testApproverLoginNegative() {
		Mockito.when(approverService.approverLogin(approverRequestDto1.getApproverEmail(),
				approverRequestDto1.getApproverPassowrd())).thenReturn(Optional.of(new Approver()));
		Integer expected = approverController.approverLogin(approverRequestDto).getStatusCodeValue();
		assertEquals(ApplicationConstants.ERROR_CODE, expected);
	}

	@Test
	public void testViewClaimsEmpty() throws GeneralException {
		Mockito.when(approverService.viewClaims(2L)).thenReturn(claimList1);
		Integer expected = approverController.viewClaims(1L).getStatusCodeValue();
		assertEquals(ApplicationConstants.ERROR_CODE, expected);
	}

	@Test
	public void testViewClaimsList() throws GeneralException {
		Mockito.when(approverService.viewClaims(1L)).thenReturn(claimList);
		Mockito.when(approverService.getClaimList(claimList)).thenReturn(approverClaimListResponseDto);
		Integer expected = approverController.viewClaims(1L).getStatusCodeValue();
		assertEquals(ApplicationConstants.SUCCESS_CODE, expected);
	}

}
