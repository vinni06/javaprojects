package com.mindtree.kalingahospital.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class PatientDto {
	
	private int patientId;
	private String patientName;
	private int billAmt;
	@JsonIgnoreProperties(value="patients", allowGetters = true, allowSetters = true)
	private DoctorDto doctorsdto;
	public PatientDto() {
		super();
	}
	public PatientDto(int patientId, String patientName, int billAmt, DoctorDto doctorsdto) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.billAmt = billAmt;
		this.doctorsdto = doctorsdto;
	}
	public int getPatientId() {
		return patientId;
	}
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public int getBillAmt() {
		return billAmt;
	}
	public void setBillAmt(int billAmt) {
		this.billAmt = billAmt;
	}
	public DoctorDto getDoctorsdto() {
		return doctorsdto;
	}
	public void setDoctorsdto(DoctorDto doctorsdto) {
		this.doctorsdto = doctorsdto;
	}
	@Override
	public String toString() {
		return "PatientDto [patientId=" + patientId + ", patientName=" + patientName + ", billAmt=" + billAmt
				+ ", doctorsdto=" + doctorsdto + "]";
	}
	
	
	

}
