package com.mindtree.university.service.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mindtree.university.dao.CollegeDao;
import com.mindtree.university.dao.daoimpl.CollegeDaoImpl;
import com.mindtree.university.entity.College;
import com.mindtree.university.entity.University;
import com.mindtree.university.exceptions.DaoExceptions;
import com.mindtree.university.exceptions.InvalidRatingException;
import com.mindtree.university.exceptions.ServiceExceptions;
import com.mindtree.university.service.CollegeService;

public class CollegeServiceImpl implements CollegeService {
     static CollegeDao collegeDaoImpl=new CollegeDaoImpl();

	@Override
	public University insertColleges(int universityId, College college) throws ServiceExceptions {
			if(college.getCollegeId()>0 || college.getCollegeId()<10) {
				try {
					University getAllDetails;
					getAllDetails = collegeDaoImpl.insertColleges(universityId, college);
					return getAllDetails;
				} catch (DaoExceptions e) {
					throw new ServiceExceptions(e.getMessage());
				}
			}else {
				throw new InvalidRatingException("please enter a valid rating");
			}
			
			
		
		
	}

	

	
	

}
