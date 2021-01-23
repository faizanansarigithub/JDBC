package com.fa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PsLoginApp {
	private static final String AUTH_QUERY="SELECT COUNT(*) FROM USERINFO WHERE UNAME=? AND PWD =?";

	public static void main(String[] args) {
		Scanner sc=null;
		String user=null;
		String pass=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int count=0;
		try {
			//read input
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("enter user name::");
				user=sc.nextLine();//gives babu
				System.out.println("enter password::");
				pass=sc.nextLine();//gives sona
				
			}
			//register jdbc driver s/w
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager");
			if(con!=null)
				ps=con.prepareStatement(AUTH_QUERY);
			//set values to the param of pre-compile SQL query
			if(ps!=null) {
				ps.setString(1,user);
				ps.setString(2,pass);
				//execute query
				rs=ps.executeQuery();
			}
			//process the ResultSet
			if(rs!=null) {
				rs.next();
				count=rs.getInt(1);
			}
			//process the result
			if(count==0)
				System.out.println("invalid user or pass");
			else
				System.out.println("Valid user and pass");
		}
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
