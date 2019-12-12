package com.mindtree.kalingahospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.kalingahospital.dto.DoctorDto;
import com.mindtree.kalingahospital.dto.PatientDto;
import com.mindtree.kalingahospital.exceptions.ApplicationException;
import com.mindtree.kalingahospital.exceptions.ServiceException;
import com.mindtree.kalingahospital.service.DoctorService;
import com.mindtree.kalingahospital.service.PatientService;

@RestController
public class ApplicationController {

	@Autowired
	DoctorService doctorServiceImpl;

	@Autowired
	PatientService patientServiceImpl;

	@PostMapping("/AssignDoctor/{doctorName}/{patientName}")
	public PatientDto assginDoctor(@PathVariable String doctorName, @PathVariable String patientName)throws ApplicationException {
		PatientDto assignedDoctor;
		try {
			assignedDoctor = doctorServiceImpl.assginDoctor(doctorName, patientName);
		} catch (ServiceException e) {
			throw new ApplicationException(e.getMessage());
		}
		return assignedDoctor;

	}

	@GetMapping("/displayDoctors")
	public List<DoctorDto> displayDoctors() throws ApplicationException {
		List<DoctorDto> doctorsList;
		try {
			doctorsList = doctorServiceImpl.getDoctors();
		} catch (ServiceException e) {
			throw new ApplicationException(e.getMessage());
		}
		return doctorsList;

	}

	@GetMapping("/displayDoctorsbyexp")
	public List<DoctorDto> displayDoctorsByExp() {
		return doctorServiceImpl.displayDoctorsByExp();

	}

	@GetMapping("/addtofile")
	public String addToFile() {
		return doctorServiceImpl.addToFile();

	}

}
