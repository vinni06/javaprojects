package com.mindtree.kalingahospital.service;

import java.util.List;

import com.mindtree.kalingahospital.dto.DoctorDto;
import com.mindtree.kalingahospital.dto.PatientDto;
import com.mindtree.kalingahospital.exceptions.ServiceException;

public interface DoctorService {

	public PatientDto assginDoctor(String doctorName, String patientName) throws ServiceException;

	public List<DoctorDto> getDoctors() throws ServiceException;

	public List<DoctorDto> displayDoctorsByExp();

	public String addToFile();

}
