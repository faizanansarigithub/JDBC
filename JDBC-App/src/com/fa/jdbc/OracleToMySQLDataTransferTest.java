package com.fa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleToMySQLDataTransferTest {
	private static final String ORA_GET_ALL_STUDENTINFO="SELECT SNAME,SADD,AVG FROM STUDENT";
	private static final String MYSQL_INSERT_STUDENTINFO="INSERT INTO STUDENT(SNAME,SADD,AVG)VALUES(?,?,?)";

	public static void main(String[] args) {
		Connection oraCon=null,mysqlCon=null;
		Statement st=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String newName=null,newAdd=null;
		float newAvg=0.0f;
		try {
			//Load JDBC driver s/w
			//Class.forName("oracle.jdbc.driver.OracleDriver");//optional
			//Class.forName("com.mysql.cj.jdbc.Driver");//optional
			//Established the connection
			oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "manager");
			mysqlCon=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root","root");
			if(oraCon!=null)
				st=oraCon.createStatement();
			if(mysqlCon!=null)
				ps=mysqlCon.prepareStatement(MYSQL_INSERT_STUDENTINFO);
			if(st!=null)
				rs=st.executeQuery(ORA_GET_ALL_STUDENTINFO);
			if(ps!=null && rs!=null) {
				while(rs.next()) {
					//get valus
					newName=rs.getString(1);
					newAdd=rs.getString(2);
					newAvg=rs.getFloat(3);
					//set values
					ps.setString(1, newName);
					ps.setString(2, newAdd);
					ps.setFloat(3, newAvg);
					ps.executeUpdate();
				}//while
				System.out.println("Records are copied from oracle DB to mysql DB table");
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objects
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(ps!=null)
					ps.close();
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
				if(mysqlCon!=null)
					mysqlCon.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(oraCon!=null)
					oraCon.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			
			
		}//finally

	}//main

}//class
