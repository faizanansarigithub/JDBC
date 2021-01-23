package com.fa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectAndNonSelectTest {

	public static void main(String[] args) {
		Scanner sc=null;
		String query=null;
		Connection con=null;
		Statement st=null;
		boolean flag=false;
		ResultSet rs=null;
		int count=0;
		
		try {
			//read input
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter select or non-select SQL query(student)");
				query=sc.nextLine();
			}//if
			
			//register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "manager");
			//create JDBC statement object
			if(con!=null)
				st=con.createStatement();
			//send and excute SQL query in DB s/w
			if(st!=null)
				flag=st.execute(query);
			//process the result
			if(flag) {
				System.out.println("select SQL query is executed");
				rs=st.getResultSet();
				//process the ResultSet
				//CURD
				if(rs!=null) {
					while(rs.next()) {
						System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+ " "+rs.getFloat(4));
					}//while
				}//if
			}//if
			else {
				System.out.println("NOn select SQL query is executed");
				count=st.getUpdateCount();
				System.out.println("no of record that are effected  "+count);
			}//else
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
			
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally

	}//main

}//class
