package com.claim.medicalclaim.controller;

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

import com.claim.medicalclaim.constants.ApplicationConstants;
import com.claim.medicalclaim.controller.HospitalController;
import com.claim.medicalclaim.dto.HospitalResponseDto;
import com.claim.medicalclaim.entity.Hospital;
import com.claim.medicalclaim.service.HospitalService;

@RunWith(MockitoJUnitRunner.class)
public class HospitalControllerTest {

	@InjectMocks
	HospitalController hospitalController;

	@Mock
	HospitalService hospitalService;

	Hospital hospital = null;
	List<Hospital> hospitalList = null;
	List<Hospital> hospitalList1 = null;
	HospitalResponseDto hospitalResponseDto = null;

	@Before
	public void before() {
		hospital = new Hospital();
		hospitalList = new ArrayList<>();
		hospitalList1 = new ArrayList<>();
		hospital.setHospitalId(1);
		hospital.setHospitalLocation("Bangalore");
		hospital.setHospitalName("Apollo");
		hospitalList.add(hospital);

		hospitalResponseDto = new HospitalResponseDto();
		hospitalResponseDto.setHospitals(hospitalList);
		hospitalResponseDto.setStatusCode(ApplicationConstants.SUCCESS_STATUS_CODE);
		hospitalResponseDto.setMessage(ApplicationConstants.SUCCESS);
	}

	@Test
	public void getHospitalsPositive() {
		Mockito.when(hospitalService.findAllHospitals()).thenReturn(hospitalList);
		Integer expected = hospitalController.getHospitals().getStatusCodeValue();
		assertEquals(ApplicationConstants.SUCCESS_STATUS_CODE,expected);
	}
	
	@Test
	public void getHospitalsNegative() {
		Mockito.when(hospitalService.findAllHospitals()).thenReturn(hospitalList1);
		Integer expected = hospitalController.getHospitals().getStatusCodeValue();
		assertEquals(ApplicationConstants.FAILURE_STATUS_CODE,expected);
	}
}
