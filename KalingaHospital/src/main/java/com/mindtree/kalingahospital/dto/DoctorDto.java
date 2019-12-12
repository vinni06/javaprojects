package com.mindtree.kalingahospital.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.mindtree.kalingahospital.entity.Patient;

public class DoctorDto {
	
	private int doctorId;
	private String doctorName;
	private int yearOfExp;
	private int salary;
	@JsonIgnoreProperties(value = {"doctorsdto"}, allowGetters = true, allowSetters = true)
	private List<PatientDto> patients;
	public DoctorDto() {
		super();
	}
	public DoctorDto(int doctorId, String doctorName, int yearOfExp, int salary, List<PatientDto> patients) {
		super();
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.yearOfExp = yearOfExp;
		this.salary = salary;
		this.patients = patients;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public int getYearOfExp() {
		return yearOfExp;
	}
	public void setYearOfExp(int yearOfExp) {
		this.yearOfExp = yearOfExp;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public List<PatientDto> getPatients() {
		return patients;
	}
	public void setPatients(List<PatientDto> patients) {
		this.patients = patients;
	}
	@Override
	public String toString() {
		return "DoctorDto [doctorId=" + doctorId + ", doctorName=" + doctorName + ", yearOfExp=" + yearOfExp
				+ ", salary=" + salary + ", patients=" + patients + "]";
	}
	

	
}
