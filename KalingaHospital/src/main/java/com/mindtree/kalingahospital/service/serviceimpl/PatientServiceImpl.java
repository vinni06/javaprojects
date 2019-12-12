package com.mindtree.kalingahospital.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.kalingahospital.repository.DoctorRepository;
import com.mindtree.kalingahospital.repository.PatientRepository;
import com.mindtree.kalingahospital.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	@Autowired
	DoctorRepository doctorRepositoryObj;
	
	@Autowired
	PatientRepository patientRepositoryObj;
}
