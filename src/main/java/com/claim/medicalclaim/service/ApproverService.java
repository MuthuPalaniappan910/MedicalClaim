package com.claim.medicalclaim.service;

import java.util.Optional;

import com.claim.medicalclaim.entity.Approver;

public interface ApproverService {

	Optional<Approver> approverLogin(String approverEmail, String approverPassword);

}
