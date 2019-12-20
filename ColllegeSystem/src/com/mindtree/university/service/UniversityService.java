package com.mindtree.university.service;

import java.util.List;

import com.mindtree.university.entity.University;
import com.mindtree.university.exceptions.AlreadyUniversityNameExistsException;
import com.mindtree.university.exceptions.ApplicationExceptions;
import com.mindtree.university.exceptions.ServiceExceptions;

public interface UniversityService {

	public String insertUniversity(University universities) throws ServiceExceptions;

	public List<University> listUniversity() throws ServiceExceptions;

	public University sortUniversities(int id) throws ServiceExceptions;

	public List<University> sortByRating(double newrating) throws ServiceExceptions;

	public String validateUniversity(String uname) throws  ServiceExceptions;

	public int validateUniversityId(int id) throws ServiceExceptions;

	
}
