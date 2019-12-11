package com.claim.medicalclaim.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.claim.medicalclaim.dto.ClaimSearchResponseDto;
import com.claim.medicalclaim.entity.Claim;
import com.claim.medicalclaim.entity.ClaimStatus;
import com.claim.medicalclaim.exception.PolicyClaimNotFoundException;
import com.claim.medicalclaim.repository.ClaimRepository;
import com.claim.medicalclaim.repository.ClaimStatusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class ClaimServiceTest {

	@Mock
	ClaimStatusRepository claimStatusRepository;

	@Mock
	ClaimRepository claimRepository;

	@InjectMocks
	ClaimServiceImpl claimServiceImpl;

	ClaimSearchResponseDto claimSearchResponseDto = null;
	Claim claim = null;
	ClaimStatus claimStatus = null;

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
		claimStatus.setClaimStatus("pending");
		claimStatus.setClaimStatusID(1L);
	}

	@Test
	public void testsearchClaimForPositive() throws PolicyClaimNotFoundException {
		Mockito.when(claimRepository.findByClaimId(1L)).thenReturn(Optional.of(claim));
		Mockito.when(claimStatusRepository.findByClaimId(claim)).thenReturn(Optional.of(claimStatus));
		ClaimSearchResponseDto response = claimServiceImpl.searchClaim(1L);
		assertEquals(claimSearchResponseDto.getClaimId(), response.getClaimId());
	}

	@Test(expected = PolicyClaimNotFoundException.class)
	public void testsearchClaimForNull() throws PolicyClaimNotFoundException {
		Optional<ClaimStatus> claim1 = Optional.ofNullable(null);
		Mockito.when(claimRepository.findByClaimId(1L)).thenReturn(Optional.of(claim));
		Mockito.when(claimStatusRepository.findByClaimId(claim)).thenReturn(claim1);
		claimServiceImpl.searchClaim(1L);
	}
}
