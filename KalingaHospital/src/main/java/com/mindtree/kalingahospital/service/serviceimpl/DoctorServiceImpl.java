package com.mindtree.kalingahospital.service.serviceimpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.kalingahospital.dto.DoctorDto;
import com.mindtree.kalingahospital.dto.PatientDto;
import com.mindtree.kalingahospital.entity.Doctor;
import com.mindtree.kalingahospital.entity.Patient;
import com.mindtree.kalingahospital.exceptions.DoctorNotFoundException;
import com.mindtree.kalingahospital.exceptions.PatientNotFoundException;
import com.mindtree.kalingahospital.exceptions.ServiceException;
import com.mindtree.kalingahospital.repository.DoctorRepository;
import com.mindtree.kalingahospital.repository.PatientRepository;
import com.mindtree.kalingahospital.service.DoctorService;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	DoctorRepository doctorRepositoryObj;

	@Autowired
	PatientRepository patientRepositoryObj;

	static ModelMapper mapper = new ModelMapper();

	@Override
	public PatientDto assginDoctor(String doctorName, String patientName) throws ServiceException {
		DoctorDto doctorDto = null;
		Doctor d = null;
		Patient p = null;

		List<Doctor> doctorsList = doctorRepositoryObj.findAll();
		int doctorcount = 0;
		for (int i = 0; i < doctorsList.size(); i++) {

			if (doctorsList.get(i).getDoctorName().equalsIgnoreCase(doctorName)) {
				doctorcount++;
				d = doctorsList.get(i);

			}

		}
		int patientcount = 0;
		List<Patient> patientList = patientRepositoryObj.findAll();
		for (int i = 0; i < patientList.size(); i++) {
			if (patientList.get(i).getPatientName().equalsIgnoreCase(patientName)) {
				patientcount++;
				p = patientList.get(i);

			}
		}
		if (p != null) {
			p.setDoctors(d);
		}

		Patient savedPatients = patientRepositoryObj.save(p);
		PatientDto patientDto = convertEntityToDto(savedPatients);
		if (doctorcount == 0) {
			throw new DoctorNotFoundException("invalid doctor");
		} else if (patientcount == 0) {
			throw new PatientNotFoundException("invalid patient");
		} else {
			return patientDto;
		}

	}

	private static PatientDto convertEntityToDto(Patient savedPatients) {

		return mapper.map(savedPatients, PatientDto.class);
	}

	@Override
	public List<DoctorDto> getDoctors() throws ServiceException {

		int flag = 0;
		List<Doctor> doctorsList = doctorRepositoryObj.findAll();
		for (int i = 0; i < doctorsList.size(); i++) {
//			if (doctorsList.get(i).getPatients().size() == 0) {
//				throw new NoPatientFoundException("no patients found");
//			}
			List<Patient> patientList = doctorsList.get(i).getPatients();

			int count = 0;
			for (int j = 0; j < patientList.size(); j++) {
				

				count += patientList.get(j).getBillAmt();

			}
			doctorsList.get(i).setSalary(count);
			doctorRepositoryObj.saveAndFlush(doctorsList.get(i));

		}
		List<Doctor> lastdoctors = doctorRepositoryObj.findAll();
		Comparator<Doctor> cmp = Comparator.comparing(Doctor::getSalary);
		Collections.sort(lastdoctors, cmp);

		List<DoctorDto> alldoctors = new ArrayList<DoctorDto>();
		for (int i = 0; i < lastdoctors.size(); i++) {
			DoctorDto dto = mapper.map(lastdoctors.get(i), DoctorDto.class);
			alldoctors.add(dto);
		}

		return alldoctors;

	}

	@Override
	public List<DoctorDto> displayDoctorsByExp() {
		List<Doctor> doctors = doctorRepositoryObj.findAll();
		List<Doctor> saveddoctors = new ArrayList<Doctor>();
		for (int i = 0; i < doctors.size(); i++) {
			if (doctors.get(i).getPatients().size() > 3) {
				saveddoctors.add(doctors.get(i));
			}
		}
		Comparator<Doctor> cmp = Comparator.comparing(Doctor::getYearOfExp);
		Collections.sort(saveddoctors, cmp);

		List<DoctorDto> alldoctors = new ArrayList<DoctorDto>();
		for (int i = 0; i < saveddoctors.size(); i++) {
			DoctorDto dto = mapper.map(saveddoctors.get(i), DoctorDto.class);
			alldoctors.add(dto);
		}

		return alldoctors;

	}

	@Override
	public String addToFile() {
		List<Doctor> doctors = doctorRepositoryObj.findAll();
		List<DoctorDto> alldoctors = new ArrayList<DoctorDto>();
		for (int i = 0; i < doctors.size(); i++) {
			DoctorDto dto = mapper.map(doctors.get(i), DoctorDto.class);
			alldoctors.add(dto);
		}
		try {
			File file = new File("D:\\chinni.txt");
			FileWriter f1 = new FileWriter(file);
			f1.write(alldoctors.toString());
			f1.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "successfully updated";

	}

}
