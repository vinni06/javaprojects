package com.mindtree.university.service.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.mindtree.university.dao.CollegeDao;
import com.mindtree.university.dao.UniversityDao;
import com.mindtree.university.dao.daoimpl.CollegeDaoImpl;
import com.mindtree.university.dao.daoimpl.UniversityDaoImpl;
import com.mindtree.university.entity.College;
import com.mindtree.university.entity.University;
import com.mindtree.university.exceptions.AlreadyUniversityNameExistsException;
import com.mindtree.university.exceptions.ApplicationExceptions;
import com.mindtree.university.exceptions.DaoExceptions;
import com.mindtree.university.exceptions.InvalidRatingException;
import com.mindtree.university.exceptions.NoSuchUniversityIdExistException;
import com.mindtree.university.exceptions.ServiceExceptions;
import com.mindtree.university.exceptions.UniversityDaoException;
import com.mindtree.university.service.UniversityService;

public class UniversityServiceImpl implements UniversityService {

	static CollegeDao collegeDaoImpl = new CollegeDaoImpl();
	static UniversityDao universityDaoImpl = new UniversityDaoImpl();

	

	@Override
	public List<University> listUniversity() throws ServiceExceptions {
		List<University> getAllList = new ArrayList<>();
		try {
			getAllList = universityDaoImpl.listUniversities();
			return getAllList;
		} catch (DaoExceptions e) {
			throw new ServiceExceptions(e.getMessage());
			
		}
		
	}

	@Override
	public University sortUniversities(int id) throws ServiceExceptions {
		University getAllList;
		try {
			getAllList = universityDaoImpl.getUniversity(id);
			Collections.sort(getAllList.getCollege());
			return getAllList;
		} catch (DaoExceptions e) {
		throw new ServiceExceptions(e.getMessage());
		}
		
	}

	@Override
	public List<University> sortByRating(double newrating) throws ServiceExceptions {
		
			try {
				if (newrating > 0 && newrating < 10) {
					List<University> getAllList = new ArrayList<>();

					List<University> savedUniversities = new ArrayList<University>();
				getAllList = universityDaoImpl.getAllDetails();
				System.out.println(getAllList.size());
				for (int i = 0; i < getAllList.size(); i++) {
					University saveduniversity = new University();
					saveduniversity.setUniversityName(getAllList.get(i).getUniversityName());
					saveduniversity.setUniversityId(getAllList.get(i).getUniversityId());
					List<College> savedColleges = new ArrayList<College>();
					for (int j = 0; j < getAllList.get(i).getCollege().size(); j++) {
						if (getAllList.get(i).getCollege().get(j).getRating() > newrating) {
							College college = new College();
							college.setCollegeId(getAllList.get(i).getCollege().get(j).getCollegeId());
							college.setCollegeName(getAllList.get(i).getCollege().get(j).getCollegeName());
							college.setRating(getAllList.get(i).getCollege().get(j).getRating());
							savedColleges.add(college);
						}

					}
					saveduniversity.setCollege(savedColleges);
					savedUniversities.add(saveduniversity);

				}
				return savedUniversities;
			} else {
				throw new InvalidRatingException("please enter a valid rating:");
			}
				
				
				
			} catch (DaoExceptions e) {
				throw new ServiceExceptions(e.getMessage());
			}
			

	}

	@Override
	public String validateUniversity(String uname) throws ServiceExceptions {
		List<University> getAllList;
		try {
			getAllList = universityDaoImpl.getAllDetails();
			for (int i = 0; i < getAllList.size(); i++) {
				if (getAllList.get(i).getUniversityName().equalsIgnoreCase(uname)) {
					throw new AlreadyUniversityNameExistsException("University Name Already Exists");

				}
			}
			return uname;
			
		} catch (DaoExceptions e) {
			throw new ServiceExceptions(e.getMessage());
		}
		

	}

	@Override
	public String insertUniversity(University universities) throws ServiceExceptions {
		
		
		try {
			return universityDaoImpl.insertUniversity(universities) ;
		} catch (DaoExceptions e) {
			throw new ServiceExceptions(e.getMessage());
		}
	}

	@Override
	public int validateUniversityId(int id) throws ServiceExceptions {
		List<University> getAllList;
		try {
			getAllList = universityDaoImpl.getAllDetails();
			for (int i = 0; i < getAllList.size(); i++) {
				if (getAllList.get(i).getUniversityId() == id) {
					return id;
					

				}
			}
			throw new NoSuchUniversityIdExistException(" NoSuchUniversityIdExist ");
			
		} catch (DaoExceptions e) {
			throw new ServiceExceptions(e.getMessage());
		}
	}

}
