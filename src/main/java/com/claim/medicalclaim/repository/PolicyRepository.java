package com.claim.medicalclaim.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.claim.medicalclaim.entity.Policy;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer>{

}
