package com.claim.medicalclaim.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claim.medicalclaim.entity.Approver;
import com.claim.medicalclaim.entity.Claim;
import com.claim.medicalclaim.entity.ClaimStatus;

@Repository
public interface ClaimStatusRepository extends JpaRepository<ClaimStatus, Long>{

Optional<ClaimStatus> findByClaimId(Claim claimId);
Optional<List<ClaimStatus>> findByClaimStatusAndApproverId(String pending, Approver approver);
}
