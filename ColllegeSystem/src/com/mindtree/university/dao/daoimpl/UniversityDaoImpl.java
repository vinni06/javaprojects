package com.mindtree.university.dao.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mindtree.university.dao.UniversityDao;
import com.mindtree.university.entity.College;
import com.mindtree.university.entity.University;
import com.mindtree.university.exceptions.DaoExceptions;
import com.mindtree.university.exceptions.DatabaseException;
import com.mindtree.university.exceptions.DuplicateUniversityId;
import com.mindtree.university.exceptions.ServiceExceptions;
import com.mindtree.university.exceptions.UniversityDaoException;
import com.mindtree.university.utility.DbConnection;
import com.mysql.jdbc.CallableStatement;

public class UniversityDaoImpl implements UniversityDao {

	@Override
	public String insertUniversity(University universities) throws DaoExceptions {
		String msg = "";
		Connection con=null;
		
			try {
				con = DbConnection.getConnection();
			} catch (DatabaseException e1) {
				throw new DaoExceptions("error in database connection");
			}
		
		try {
			
			if (con != null) {
				String query = "{call insertUniversity(?,?)}";
				
				  CallableStatement callableStatement = (CallableStatement) con.prepareCall(query);
				  
				 callableStatement.setInt(1, universities.getUniversityId());
				 callableStatement.setString(2, universities.getUniversityName());
				 callableStatement.executeUpdate();
				msg = "Inserted successfully";
				callableStatement.close();
			}

		con.close();
		}

		catch (Exception e) {
//			System.out.println(e.getMessage());
			throw new DaoExceptions("error in inserting universities");
		}
		return msg;

	}

	@Override
	public List<University> listUniversities() throws DaoExceptions {
		List<University> listUniversities = new ArrayList<>();
		
			Connection con=null;
			try {
				con = DbConnection.getConnection();
			} catch (DatabaseException e1) {
				throw new DaoExceptions(e1.getMessage());
			}
			try {
			if (con != null) {
				String getList = "{call getAlluniversity()}";
				CallableStatement getUniversity=(CallableStatement) con.prepareCall(getList);
				ResultSet rs = getUniversity.executeQuery();
				while (rs.next()) {
					int uid = rs.getInt("uid");
					String uName = rs.getString("uname");
					University universityList = new University(uid, uName, null);
					listUniversities.add(universityList);

				}
				rs.close();
				getUniversity.close();
				con.close();
			}
			

		} catch (Exception e) {
			throw new DaoExceptions(e.getMessage());
		}
		return listUniversities;

	}

	@Override
	public University getUniversity(int id) throws DaoExceptions {
	    List<University> getUniniversities=new ArrayList<>();
	    List<College> colleges = new ArrayList<College>();
	    University university = new University();
	    CallableStatement st=null;
	    
	   
	    	Connection con;
			try {
				con = DbConnection.getConnection();
			} catch (DatabaseException e1) {
				throw new DaoExceptions(e1.getMessage());
			}
	    	
	    	try
	 	    {
	    	if(con!=null)
	    	{
	    	
	    	String str = "select collegeId,collegeName,rating from college where uid = ?";
	    	PreparedStatement ps = con.prepareStatement(str);
	    	ps.setInt(1, id);
	    	ResultSet rs = ps.executeQuery();
	    	while(rs.next()) {
	    		College college = new College();
	    		college.setCollegeId(rs.getInt(1));
	    		college.setCollegeName(rs.getString(2));
	    		college.setRating(rs.getDouble(3));
	    		colleges.add(college);
	    		}
	    	}
	    	String str1 = "select * from university where uid = ?";
	    	PreparedStatement ps = con.prepareStatement(str1);
	    	ps.setInt(1, id);
	    	ResultSet rs1 = ps.executeQuery();
	    	while(rs1.next()) {
	    		university.setUniversityId(rs1.getInt(1));
	    		university.setUniversityName(rs1.getString(2));
	    		}
	    	university.setCollege(colleges);
	    	
	    	return university;
	    	
	    	}
	    catch(Exception e)
	    
	    {
	    	throw new DaoExceptions("error in getting university");
	    }
		
	}

	@Override
	public List<University> getAllDetails() throws DaoExceptions {
		
		List<University> listUniversities = new ArrayList<>();
		
			Connection con;
			try {
				con = DbConnection.getConnection();
			} catch (DatabaseException e1) {
				throw new DaoExceptions(e1.getMessage());
			}
			try {
			if (con != null) {	
				String str1 = "select * from university";
		    	PreparedStatement ps = con.prepareStatement(str1);
		    	ResultSet rs1 = ps.executeQuery();
		    	while(rs1.next()) {
		    		University university = new University();
		    		university.setUniversityId(rs1.getInt(1));
		    		university.setUniversityName(rs1.getString(2));
		    		listUniversities.add(university);
		    		}
//		    	System.err.println(listUniversities);
		    	
				for(int i =0;i<listUniversities.size();i++) {
					List<College> listColleges = new ArrayList<>();
					String str = "select collegeId,collegeName,rating from college where uid = ? ";
			    	PreparedStatement ps1 = con.prepareStatement(str);
			    	ps1.setInt(1, listUniversities.get(i).getUniversityId());
			    	ResultSet rs = ps1.executeQuery();
			    	while(rs.next()) {
			    		College college = new College();
			    		college.setCollegeId(rs.getInt(1));
			    		college.setCollegeName(rs.getString(2));
			    		college.setRating(rs.getDouble(3));
			    		listColleges.add(college);
			    		}
			    	listUniversities.get(i).setCollege(listColleges);
			    	}
			    	
				
				}
				
				
				
				
				
//				String getList = "{call getAllDetails()}";
//				CallableStatement getUniversity=(CallableStatement) con.prepareCall(getList);
//				ResultSet rs = getUniversity.executeQuery();
//				while (rs.next()) {
//					int uid = rs.getInt("uid");
//					String uName = rs.getString("uname");
//					int collegeId=rs.getInt(3);
//					String collegeName=rs.getString(4);
//					double rating=rs.getDouble(5);
//					College c=new College();
//					c.setCollegeId(collegeId);
//					c.setCollegeName(collegeName);
//					c.setRating(rating);
//					listColleges.add(c);
//					University savedUniversity=new University();
//					savedUniversity.setUniversityId(uid);
//					savedUniversity.setUniversityName(uName);
//					savedUniversity.setCollege(listColleges);			
//					listUniversities.add(savedUniversity);
//
//				}
//				rs.close();
//				getUniversity.close();
//				con.close();
			
			return listUniversities;
		} catch (Exception e) {
			throw new DaoExceptions("error in getting all details");
		}
		
		
	}

}
