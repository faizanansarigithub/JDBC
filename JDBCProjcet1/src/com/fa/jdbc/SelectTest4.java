package com.fa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * write a JDBC App to get emps count from emp DB table?
 * version 1.0
 * author team-fa
 *  select count(*) from emp;
 */

public class SelectTest4 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		try {
			//register jdbc driver
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager");
			if(con!=null)
				st=con.createStatement();
			//prepare SQL query
			// select count(*) from emp;
			query=" SELECT COUNT(*) FROM EMP";
			//send and execute SQL query in DB s/w
			if(st!=null)
				rs=st.executeQuery(query);
			//process the ResultSet object
			if(rs!=null) {
				rs.next();
				//System.out.println("record count ::"+rs.getInt(1));
				System.out.println("record count ::"+rs.getInt("COUNT(*)"));
			}//if

		}//try
		catch(SQLException se) {
			//se.printStackTrace();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		finally {
			//close jdbc object
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}

			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}

			try {
				if(con!=null)
					con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally

	}//main
	
}//class
