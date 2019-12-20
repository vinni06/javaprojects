package com.mindtree.university.entity;

import java.util.List;

public class University {
	private int universityId;
	private String universityName;
	List<College> college = null;

	public University() {
		super();
	}

	public University(int universityId, String universityName, List<College> college) {
		super();
		this.universityId = universityId;
		this.universityName = universityName;
		this.college = college;
	}

	public int getUniversityId() {
		return universityId;
	}

	public void setUniversityId(int universityId) {
		this.universityId = universityId;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public List<College> getCollege() {
		return college;
	}

	public void setCollege(List<College> college) {
		this.college = college;
	}

	@Override
	public String toString() {
		return "University ["
				+ "universityId=" + universityId + ","
				+ " universityName=" + universityName + ", "
			    + "college="
				+ college + "]";
	}
	

	
}
