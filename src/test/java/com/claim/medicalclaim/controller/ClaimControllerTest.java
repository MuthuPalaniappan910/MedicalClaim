package com.claim.medicalclaim.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.claim.medicalclaim.dto.ClaimSearchResponseDto;
import com.claim.medicalclaim.exception.PolicyClaimNotFoundException;
import com.claim.medicalclaim.service.ClaimService;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClaimControllerTest {
	
	@InjectMocks
	ClaimController claimController;
	
	@Mock
	ClaimService claimService;
	
	ClaimSearchResponseDto claimSearchResponseDto=null;
	
	@Before
	public void before() {
		claimSearchResponseDto=new ClaimSearchResponseDto();
		claimSearchResponseDto.setAmountSanctioned(1000.00);
		claimSearchResponseDto.setClaimId(1L);
		claimSearchResponseDto.setClaimStatus("Pending");
		claimSearchResponseDto.setComments("Approved");
	}
	
	@Test
	public void testSearchClaimForSuccess() throws PolicyClaimNotFoundException {
		Mockito.when(claimService.searchClaim(1L)).thenReturn(claimSearchResponseDto);
		Integer response=claimController.searchClaim(1L).getStatusCodeValue();
		assertEquals(HttpStatus.OK.value(), response);
		
		
	}

}
