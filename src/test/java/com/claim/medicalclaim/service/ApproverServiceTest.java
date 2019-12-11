package com.claim.medicalclaim.service;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.claim.medicalclaim.dto.ApproverRequestDto;
import com.claim.medicalclaim.dto.ApproverResponseDto;
import com.claim.medicalclaim.entity.Approver;
import com.claim.medicalclaim.repository.ApproverRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ApproverServiceTest {
	@Mock
	ApproverRepository approverRepository;

	@InjectMocks
	ApproverServiceImpl approverServiceImpl;

	ApproverResponseDto approverResponseDto = null;
	ApproverRequestDto approverRequestDto = null;
	Approver approver = null;

	@Before
	public void before() {
		approver = new Approver();
		approverRequestDto = new ApproverRequestDto();
		approver.setApproverEmail("muthu@gmail.com");
		approver.setApproverPassword("muthu123");

		approverRequestDto.setApproverEmail("muthu@gmail.com");
		approverRequestDto.setApproverPassowrd("muthu123");
	}

	@Test
	public void approverLoginPositive() {
		Mockito.when(approverRepository.findByApproverEmailAndApproverPassword(approverRequestDto.getApproverEmail(),
				approverRequestDto.getApproverPassowrd())).thenReturn(Optional.of(new Approver()));
		Optional<Approver> expected = approverServiceImpl.approverLogin(approverRequestDto.getApproverEmail(),
				approverRequestDto.getApproverPassowrd());
		assertEquals(true, expected.isPresent());
	}
}
