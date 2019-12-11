package com.claim.medicalclaim.controller;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.claim.medicalclaim.constants.ApplicationConstants;
import com.claim.medicalclaim.dto.ApproverRequestDto;
import com.claim.medicalclaim.dto.ApproverResponseDto;
import com.claim.medicalclaim.entity.Approver;
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

	@Before
	public void before() {
		approverRequestDto = new ApproverRequestDto();
		approverRequestDto.setApproverEmail("muthu@gmail.com");
		approverRequestDto.setApproverPassword("muthu123");

		approverRequestDto1 = new ApproverRequestDto();
		approverRequestDto1.setApproverEmail("muthu4@gmail.com");
		approverRequestDto1.setApproverPassword("muthu123");

		approverResponseDto = new ApproverResponseDto();
		approverResponseDto.setStatusCode(ApplicationConstants.SUCCESS_CODE);
		approverResponseDto.setMessage(ApplicationConstants.SUCCESS_MESSAGE);

		approverResponseDto1 = new ApproverResponseDto();
		approverResponseDto1.setStatusCode(ApplicationConstants.ERROR_CODE);
		approverResponseDto1.setMessage(ApplicationConstants.ERROR_MESSAGE);
	}

	@Test
	public void approverLoginPositive() {
		Mockito.when(approverService.approverLogin(approverRequestDto.getApproverEmail(),
				approverRequestDto.getApproverPassword())).thenReturn(Optional.of(new Approver()));
		Integer expected = approverController.approverLogin(approverRequestDto).getStatusCodeValue();
		assertEquals(ApplicationConstants.SUCCESS_CODE, expected);
	}

	@Test
	public void approverLoginNegative() {
		Mockito.when(approverService.approverLogin(approverRequestDto1.getApproverEmail(),
				approverRequestDto1.getApproverPassword())).thenReturn(Optional.of(new Approver()));
		Integer expected = approverController.approverLogin(approverRequestDto).getStatusCodeValue();
		assertEquals(ApplicationConstants.ERROR_CODE, expected);
	}

}
