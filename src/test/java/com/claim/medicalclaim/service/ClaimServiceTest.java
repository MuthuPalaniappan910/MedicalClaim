package com.claim.medicalclaim.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.claim.medicalclaim.dto.RaiseClaimRequestDto;
import com.claim.medicalclaim.exception.GeneralException;
import com.claim.medicalclaim.repository.ApproverRepository;
import com.claim.medicalclaim.repository.ClaimRepository;
import com.claim.medicalclaim.repository.ClaimStatusRepository;
import com.claim.medicalclaim.repository.PolicyRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ClaimServiceTest {
	@InjectMocks
	ClaimServiceImpl claimServiceImpl;
	@Mock
	ClaimRepository claimRepository;
	@Mock
	PolicyRepository policyRepository;

	@Mock
	ClaimStatusRepository claimStatusRepository;

	@Mock
	ApproverRepository approverRepository;

	RaiseClaimRequestDto raiseClaimRequestDto;
	@Before
	public void before() {
		raiseClaimRequestDto.set
	}

	@Test
	public void raiseClaim() throws GeneralException {

	}
}
