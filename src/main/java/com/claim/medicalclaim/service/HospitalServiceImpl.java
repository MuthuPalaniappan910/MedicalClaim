package com.claim.medicalclaim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.claim.medicalclaim.entity.Hospital;
import com.claim.medicalclaim.repository.HospitalRepository;


@Service
public class HospitalServiceImpl implements HospitalService{
	
	@Autowired
	HospitalRepository hospitalRepository;

	@Override
	public List<Hospital> findAllHospitals() {
		return hospitalRepository.findAll();
	}

}
