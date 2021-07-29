package com.fa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsert {
	private static final String INSERT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";
 
	public static void main(String[] args) {
		Scanner sc=null;
		String sname=null,sadd=null;
		int sno=0;
		float avg=0.0f;
		Connection con=null;
		PreparedStatement ps=null, ps1=null;
		int result=0,result1=0;
		ResultSet rs=null;
		String dname=null;
		
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter student number");
				sno=sc.nextInt();
				System.out.println("Enter student name");
				sname=sc.next();
				System.out.println("Enter student Address");
				sadd=sc.next();
				System.out.println("Enter student Average");
				avg=sc.nextFloat();
				
				System.out.println("Enter student Name which one You Delete");
				dname=sc.next();
			}//if
			//Register Oracle 
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "manager");
			if(con!=null)
				ps=con.prepareStatement(INSERT_QUERY);
			 	ps1=con.prepareStatement("DELETE FROM STUDENT WHERE SNAME=?");
			if(ps!=null) {
				ps.setInt(1, sno);
				ps.setString(2, sname);
				ps.setString(3, sadd);
				ps.setFloat(4, avg);
				ps1.setString(1, dname);
				result=ps.executeUpdate();
				result1=ps1.executeUpdate();
			}
			if(result==0)
				System.out.println(result+" Record are not  inserted");
			else
				System.out.println(result+ " Record are inserted");
			
			if(result1==0)
				System.out.println(result1+" Record are not  Deleted");
			else
				System.out.println(result1+ " Record are delete");
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
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
			catch(Exception se) {
				se.printStackTrace();
			}
		}//finally

	}//main

}//class
