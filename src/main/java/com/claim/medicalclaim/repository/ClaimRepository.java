package com.claim.medicalclaim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claim.medicalclaim.entity.Claim;
@Repository
public interface ClaimRepository extends JpaRepository<Claim, Long> {

}
