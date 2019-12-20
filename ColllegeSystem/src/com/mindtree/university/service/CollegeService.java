package com.mindtree.university.service;

import java.util.List;
import java.util.Map;

import com.mindtree.university.entity.College;
import com.mindtree.university.entity.University;
import com.mindtree.university.exceptions.ServiceExceptions;

public interface CollegeService {

	public University insertColleges(int universityId, College c) throws ServiceExceptions;

	

	

}
