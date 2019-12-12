package com.mindtree.kalingahospital.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Doctor {
	
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int doctorId;
	private String doctorName;
	private int yearOfExp;
	private int salary;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "doctors")
	private List<Patient> patients;

	public Doctor() {
		super();
	}

	public Doctor(int doctorId, String doctorName, int yearOfExp, int salary, List<Patient> patients) {
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

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	@Override
	public String toString() {
		return "Doctor [doctorId=" + doctorId + ", doctorName=" + doctorName + ", yearOfExp=" + yearOfExp + ", salary="
				+ salary + ", patients=" + patients + "]";
	}
	
	
	
	

}
