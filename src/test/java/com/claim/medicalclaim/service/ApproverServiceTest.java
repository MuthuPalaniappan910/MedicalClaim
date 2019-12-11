package com.claim.medicalclaim.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.claim.medicalclaim.constants.ApplicationConstants;
import com.claim.medicalclaim.dto.ApproverClaimListResponseDto;
import com.claim.medicalclaim.dto.ApproverRequestDto;
import com.claim.medicalclaim.dto.ApproverResponseDto;
import com.claim.medicalclaim.dto.ClaimListDto;
import com.claim.medicalclaim.entity.Approver;
import com.claim.medicalclaim.entity.Claim;
import com.claim.medicalclaim.entity.ClaimStatus;
import com.claim.medicalclaim.exception.ApproverInvalidException;
import com.claim.medicalclaim.exception.ClaimInvalidException;
import com.claim.medicalclaim.repository.ApproverRepository;
import com.claim.medicalclaim.repository.ClaimRepository;
import com.claim.medicalclaim.repository.ClaimStatusRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ApproverServiceTest {
	@Mock
	ApproverRepository approverRepository;

	@Mock
	ClaimStatusRepository claimStatusRepository;

	@Mock
	ClaimRepository claimRepository;

	@InjectMocks
	ApproverServiceImpl approverServiceImpl;

	ApproverResponseDto approverResponseDto = null;
	ApproverRequestDto approverRequestDto = null;
	Approver approver = null;
	Approver approver1 = null;

	List<ClaimStatus> claimList = new ArrayList<>();
	List<ClaimStatus> claimList1 = null;

	Claim claim = null;
	ClaimListDto claimListDto = null;
	List<ClaimListDto> claimListDtoList = new ArrayList<>();

	@Before
	public void before() {
		approver = new Approver();
		approverRequestDto = new ApproverRequestDto();
		approver.setApproverEmail("muthu@gmail.com");
		approver.setApproverPassword("muthu123");

		approverRequestDto.setApproverEmail("muthu@gmail.com");
		approverRequestDto.setApproverPassowrd("muthu123");

		approver1 = new Approver();
		approver1.setApproverId(2L);
		ClaimStatus claimStatus = new ClaimStatus();
		claimStatus.setApproverId(approver1);
		claimStatus.setClaimStatus("Pending");
		claimList1 = new ArrayList<>();
		claimList1.add(claimStatus);

		claim = new Claim();
		claimListDto = new ClaimListDto();
		claimList1 = new ArrayList<>();
		claimStatus.setClaimId(claim);
		claimStatus.setApproverId(approver);
		claimList1.add(claimStatus);
		claim.setClaimId(1L);
		claimListDto.setClaimraisedDate(LocalDate.now());
		claimListDto.setClaimId(1L);
		claimListDtoList.add(claimListDto);
	}

	@Test
	public void testApproverLoginPositive() {
		Mockito.when(approverRepository.findByApproverEmailAndApproverPassword(approverRequestDto.getApproverEmail(),
				approverRequestDto.getApproverPassowrd())).thenReturn(Optional.of(new Approver()));
		Optional<Approver> expected = approverServiceImpl.approverLogin(approverRequestDto.getApproverEmail(),
				approverRequestDto.getApproverPassowrd());
		assertEquals(true, expected.isPresent());
	}

	@Test(expected = ApproverInvalidException.class)
	public void testApproverInvalid() throws ClaimInvalidException, ApproverInvalidException {
		Mockito.when(approverRepository.findByApproverId(1L)).thenReturn(Optional.of(new Approver()));
		List<ClaimStatus> expected = approverServiceImpl.viewClaims(2L);
		String message = ApplicationConstants.APPROVER_INVALID;
		assertEquals(message, expected.size());
	}

	@Test(expected = ClaimInvalidException.class)
	public void testClaimInvalid() throws ClaimInvalidException, ApproverInvalidException {
		Mockito.when(approverRepository.findByApproverId(1L)).thenReturn(Optional.of(approver1));
		Mockito.when(claimStatusRepository.findByClaimStatusAndApproverId("Approved", approver1))
				.thenReturn(Optional.of(claimList));
		List<ClaimStatus> expected = approverServiceImpl.viewClaims(1L);
		String message = ApplicationConstants.CLAIM_INVALID;
		assertEquals(message, expected.size());
	}

	@Test
	public void testApproverValid() throws ClaimInvalidException, ApproverInvalidException {
		Mockito.when(approverRepository.findByApproverId(1L)).thenReturn(Optional.of(approver1));
		Mockito.when(claimStatusRepository.findByClaimStatusAndApproverId("Pending", approver1))
				.thenReturn(Optional.of(claimList1));
		List<ClaimStatus> expected = approverServiceImpl.viewClaims(1L);
		assertEquals(claimList1.size(), expected.size());
	}

	@Test
	public void testGetClaims() {
		Mockito.when(claimRepository.findByClaimId(1L)).thenReturn(Optional.of(claim));
		ApproverClaimListResponseDto expected = approverServiceImpl.getClaimList(claimList1);
		assertEquals(claimListDtoList.size(), expected.getClaimListDto().size());
	}
	
	@Test
	public void testGetClaimsEmpty() {
		Mockito.when(claimRepository.findByClaimId(1L)).thenReturn(Optional.of(claim));
		ApproverClaimListResponseDto expected = approverServiceImpl.getClaimList(claimList);
		assertEquals(0, expected.getClaimListDto().size());
	}
}
