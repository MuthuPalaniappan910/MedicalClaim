package com.claim.medicalclaim.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.claim.medicalclaim.entity.Hospital;
import com.claim.medicalclaim.repository.HospitalRepository;

@RunWith(MockitoJUnitRunner.class)
public class HospitalServiceTest {
	@Mock
	HospitalRepository hospitalRepository;

	@InjectMocks
	HospitalServiceImpl hospitalServiceImpl;

	Hospital hospital = null;
	List<Hospital> hospitalList = null;

	@Before
	public void before() {
		hospital = new Hospital();
		hospitalList = new ArrayList<>();
		hospital.setHospitalId(1);
		hospital.setHospitalLocation("Bangalore");
		hospital.setHospitalName("Apollo");
		hospitalList.add(hospital);
	}

	@Test
	public void getHospitalsPositive() {
		Mockito.when(hospitalRepository.findAll()).thenReturn(hospitalList);
		List<Hospital> expected = hospitalServiceImpl.findAllHospitals();
		assertEquals(hospitalList.size(), expected.size());
	}
}
