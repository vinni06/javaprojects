package com.mindtree.university.dao;

import java.util.List;

import com.mindtree.university.entity.University;
import com.mindtree.university.exceptions.DaoExceptions;
import com.mindtree.university.exceptions.UniversityDaoException;

public interface UniversityDao {

	String insertUniversity(University universities) throws DaoExceptions ;

	List<University> listUniversities() throws DaoExceptions;

	public University getUniversity(int id) throws DaoExceptions;

	public List<University> getAllDetails() throws DaoExceptions;

	

}
