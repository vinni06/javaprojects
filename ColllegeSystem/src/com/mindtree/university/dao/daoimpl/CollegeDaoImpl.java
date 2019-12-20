package com.mindtree.university.dao.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mindtree.university.dao.CollegeDao;
import com.mindtree.university.entity.College;
import com.mindtree.university.entity.University;
import com.mindtree.university.exceptions.DaoExceptions;
import com.mindtree.university.exceptions.DatabaseException;
import com.mindtree.university.utility.DbConnection;
import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Statement;

public class CollegeDaoImpl implements CollegeDao {

	@Override
	public University insertColleges(int universityId, College college) throws DaoExceptions  {
		

			Connection con;
			try {
				con = DbConnection.getConnection();
				
			} catch (DatabaseException e1) {
				throw new DaoExceptions(e1.getMessage());
			}
			try {
				
			if (con != null) {
				String msg = "";
				PreparedStatement st = null;
				List<University> allUniversties = new ArrayList<>();
				String query = "insert into college (collegeName,rating,uId) values(?,?,?);";
				st = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				st.setString(1, college.getCollegeName());
				st.setDouble(2, college.getRating());
				st.setInt(3, universityId);
				st.executeUpdate();
				ResultSet rsss= st.getGeneratedKeys();
				if(rsss.next()) {
					college.setCollegeId(rsss.getInt(1));
				}
				msg = "Inserted successfully";
				University university = new University();
				
				
				
				String str1 = "{call getUniversity(?)}";
				CallableStatement cs= (CallableStatement) con.prepareCall(str1);
				cs.setInt(1,universityId );
				ResultSet rs = cs.executeQuery();
				while(rs.next()) {
					university.setUniversityId(rs.getInt(1));
					university.setUniversityName(rs.getString(2));
				}
				List<College> colleges = new ArrayList<College>();
				colleges.add(college);
				university.setCollege(colleges);
			return university;
				
				
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			throw new DaoExceptions("error in inserting colleges");
		}
		return null;
	}
}
			
				
				
//				
//				String getAll = "select id,name,collegeId,collegeName,rating from college inner join university on college.uId=university.id;";
//				st = con.prepareStatement(getAll);
//				ResultSet rs = st.executeQuery();
//				while (rs.next()) {
//					int uid = rs.getInt("id");
//					String uName = rs.getString("name");
//					int collegeId = rs.getInt("collegeId");
//					String collegeName = rs.getString("collegeName");
//					
//					double rating = rs.getDouble("rating");
//					College c=new College();
//					List<College> listColleges = new ArrayList<>();
//					c.setCollegeId(collegeId);
//					c.setCollegeName(collegeName);
//					c.setRating(rating);
//					listColleges.add(c);
//					University listUniversities = new University(uid, uName, listColleges);
//					allUniversties.add(listUniversities);



