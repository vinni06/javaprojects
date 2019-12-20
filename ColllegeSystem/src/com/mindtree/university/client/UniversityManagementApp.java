package com.mindtree.university.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.mindtree.university.entity.College;
import com.mindtree.university.entity.University;
import com.mindtree.university.exceptions.AlreadyUniversityNameExistsException;
import com.mindtree.university.exceptions.ApplicationExceptions;
import com.mindtree.university.exceptions.ServiceExceptions;
import com.mindtree.university.service.CollegeService;
import com.mindtree.university.service.UniversityService;
import com.mindtree.university.service.serviceimpl.CollegeServiceImpl;
import com.mindtree.university.service.serviceimpl.UniversityServiceImpl;

public class UniversityManagementApp {

	static Scanner sc = new Scanner(System.in);
	static CollegeService collegeServiceimpl = new CollegeServiceImpl();
	static UniversityService universityServiceimpl = new UniversityServiceImpl();

	public static void main(String[] args) {
		University universities = null;
		boolean isvalidate = true;
		while (isvalidate) {
			System.out.println("*********MENU**********");
			System.out.println(
					"1.Register University \n2.Register College\n3.For an entered university ID display all the list of colleges sorted\n"
							+ "4.Display all the College Details whose rating is greater than an entered rating.\r\n"
							+ "5.exit");
			System.out.println("enter the choice:");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				
				try {
					System.out.println("enter UniversityId:");
					int uId = sc.nextInt();
					System.out.println("enter UniversityName:");
					String uname = sc.next();
					String validateName;
					validateName = universityServiceimpl.validateUniversity(uname);
					universities = new University(uId, validateName, null);
					String message = universityServiceimpl.insertUniversity(universities);
					System.out.println("added successfully");
				} catch (ServiceExceptions e1) {
					System.err.println(e1.getMessage());
//					e1.printStackTrace();
				
				}
					
				break;

			case 2:
				try {
				College c = new College();
				List<University> getAllUniversities = new ArrayList<>();
				getAllUniversities = universityServiceimpl.listUniversity();
				for (University list : getAllUniversities) {
					System.out.println(
							"universityId:" + list.getUniversityId() + "\tuniversityName:" + list.getUniversityName());
				}
				System.out.println("enter university id:");
				int universityId = sc.nextInt();
				System.out.println("enter college name");
				String collegeName = sc.next();
				c.setCollegeName(collegeName);
				System.out.println("enter rating:");
				double rating = sc.nextDouble();
				c.setRating(rating);
				University insertColleges;
				
					insertColleges = collegeServiceimpl.insertColleges(universityId, c);
					System.out.println(insertColleges);
				} catch (ServiceExceptions e1) {
					System.out.println(e1.getMessage());
				}		
				break;

			case 3:
				
				try {
					System.out.println("enter university id:");
					int id = sc.nextInt();
					int universityId = universityServiceimpl.validateUniversityId(id);
					University sortUniversity = universityServiceimpl.sortUniversities(universityId);
					System.out.println(sortUniversity);
				} catch (ServiceExceptions e1) {
					System.err.println(e1.getMessage());
					
				}
				
				break;
			case 4:
				System.out.println("enter rating:");
				double newrating = sc.nextDouble();
				List<University> displayByRating;
				try {
					displayByRating = universityServiceimpl.sortByRating(newrating);
					for (int i = 0; i < displayByRating.size(); i++) {
						if (displayByRating.get(i).getCollege().size() != 0) {
							System.err.println(displayByRating.get(i));
						}
					}

				} catch (ServiceExceptions e) {
					System.err.println(e.getMessage());
					
				}
			case 5:System.exit(0);
			       break;



			}

		}

	}

}
