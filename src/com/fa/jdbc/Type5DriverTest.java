package com.fa.jdbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Type5DriverTest {
	//add library in configure build path

	public static void main(String[] args) {
		
		Statement st=null;
		ResultSet rs=null;
		boolean flag=false;
		Connection con=null;
		try {			
			//Resister oracle driver
				Class.forName("com.ddtek.jdbc.oracle.OracleDriver");
			//establish the connectoin
				con=DriverManager.getConnection("jdbc:datadirect:oracle://localhost:1521;serviceName=ORCL", "system", "manager");
			//con=DriverManager.getConnection("jdbc:datadirect:oracle://localhost:1521;serviceName=ORCL","system","manager");
			//create statement objects
			if(con!=null) 
				st=con.createStatement();
			if(st!=null)
				rs=st.executeQuery("SELECT SNO,SNAME,SADD,AVG FROM STUDENT");
			//process the ResultSet
			if(rs!=null) {
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				}
				if(flag)
					System.out.println("Record  Found");
				else
					System.out.println("Record not found");
			}
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
