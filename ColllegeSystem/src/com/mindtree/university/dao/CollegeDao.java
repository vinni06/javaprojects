package com.mindtree.university.dao;

import java.util.List;

import com.mindtree.university.entity.College;
import com.mindtree.university.entity.University;
import com.mindtree.university.exceptions.DaoExceptions;

public interface CollegeDao {

	public University insertColleges(int universityId, College college) throws DaoExceptions;

	

}
