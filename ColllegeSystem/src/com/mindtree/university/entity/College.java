package com.mindtree.university.entity;

public class College implements Comparable<College>{
	private int collegeId;
	private String collegeName;
	private double rating;

	public College() {
		super();
	}

	public College(int collegeId, String collegeName, double rating) {
		super();
		this.collegeId = collegeId;
		this.collegeName = collegeName;
		this.rating = rating;
	}

	public int getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "College [collegeId=" + collegeId + ", collegeName=" + collegeName + ", rating=" + rating + "]";
	}

	@Override
	public int compareTo(College arg0) {
		if(this.rating > arg0.rating) {
			return -1;
		}else if(this.rating < arg0.rating)
		{
			return 1;
		}else {
			return 0;
		}
	}
	

	
}
