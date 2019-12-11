package com.claim.medicalclaim.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medicalclaim.entity.Approver;
import com.claim.medicalclaim.repository.ApproverRepository;

@Service
public class ApproverServiceImpl implements ApproverService {
	@Autowired
	ApproverRepository approverRepository;

	@Override
	public Optional<Approver> approverLogin(String approverEmail, String approverPassword) {
		return approverRepository.findByApproverEmailAndApproverPassword(approverEmail,approverPassword);
	}

}
