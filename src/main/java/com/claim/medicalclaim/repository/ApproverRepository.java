package com.claim.medicalclaim.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.claim.medicalclaim.entity.Approver;
@Repository
public interface ApproverRepository {
	Optional<Approver> findByApproverEmailAndApproverPassword(String approverEmail, String approverPassword);
}
