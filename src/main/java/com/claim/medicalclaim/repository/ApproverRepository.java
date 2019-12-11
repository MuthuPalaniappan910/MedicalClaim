package com.claim.medicalclaim.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claim.medicalclaim.entity.Approver;
@Repository
public interface ApproverRepository extends JpaRepository<Approver, Long>{
	Optional<Approver> findByApproverEmailAndApproverPassword(String approverEmail, String approverPassword);
	Optional<Approver> findByAilment(String ailment);
}
