package com.mindtree.university.utility;

import java.sql.Connection;
import java.sql.DriverManager;

import com.mindtree.university.exceptions.DatabaseException;

public class DbConnection {

	private static String DBURL = "jdbc:mysql://localhost:3306/university";
	private static String USERNAME = "root";
	private static String PASSWORD = "Welcome123";

	public static Connection getConnection() throws DatabaseException {
		Connection con = null;
		try {
			con = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
			
		} catch (Exception e) {
//			e.printStackTrace();
			throw new DatabaseException("error in connection");
		}
		return con;
	}

	public static String closeConnection(Connection con) throws DatabaseException {
		String result = " ";
		try {
			con.close();
			result = "connection closed successfully";
		} catch (Exception e) {
//			e.printStackTrace();
			throw new DatabaseException("error in closing connection");
		}
		return result;
	}
}
