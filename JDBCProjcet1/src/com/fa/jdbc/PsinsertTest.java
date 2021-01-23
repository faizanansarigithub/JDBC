package com.fa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsinsertTest {
	private static final String INSERT_STUDENT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";

	public static void main(String[] args) {
		Scanner sc=null;
		int count=0;
		Connection con=null;
		PreparedStatement ps=null;
		int no=0;
		String name=null,addrs=null;
		float avg=0.0f;
		int result=0;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter student count");
				count =sc.nextInt();
			}
			//register jdbc driver s/w
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager");
			if(con!=null) {
				ps=con.prepareStatement(INSERT_STUDENT_QUERY);
				
			}
			//READ each student details and send them pre-compile query param values for multiple time
			if(ps!=null && sc!=null) {
				for(int i=1;i<=count;++i) {
					System.out.println("enter "+i+"student details");
					System.out.println("number::");
					no=sc.nextInt();
					System.out.println("name::");
					name=sc.next();
					System.out.println("address::");
					addrs=sc.next();
					System.out.println("avg::");
					avg=sc.nextFloat();
					//set each student details to query param values
					ps.setInt(1, no);
					ps.setString(2, name);
					ps.setString(3, addrs);
					ps.setFloat(4, avg);
					//execute the query
					result=ps.executeUpdate();
					//process the result
					if(result==0)
						System.out.println(i+" student details are not inserted");
					else
						System.out.println(i+" student details are inserted");
				}//for
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
				if(ps!=null)
					ps.close();
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
