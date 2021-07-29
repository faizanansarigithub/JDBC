package com.fa.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ResultSetMetaDataTest {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		boolean flag=false;
		try {
		//Resister oracle driver
		//	Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish the connectoin
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager");
			//create statement objects
			if(con!=null) 
				st=con.createStatement();
			if(st!=null)
				rs=st.executeQuery("SELECT * FROM STUDENT");
			//create ResultSetMetaData obj
			ResultSetMetaData rsmd=null;
			

				rsmd=rs.getMetaData();
			//print column name
			if(rsmd!=null) {
				int count =rsmd.getColumnCount();
				for(int i=1;i<=count;i++) {
					System.out.print(rsmd.getColumnLabel(i)+"     ");
					System.out.print(rsmd.getColumnTypeName(i)+"     ");
				}
				System.out.println();
			}
			//process the ResultSet
			if(rs!=null) {
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				}
				if(flag)
					System.out.println("REcord  Found");
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
