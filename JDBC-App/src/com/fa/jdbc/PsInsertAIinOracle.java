package com.fa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class PsInsertAIinOracle {
	private static final String ORACLE_INSERT_QUERY = "INSERT INTO STUDENT VALUES (?,?,?,?)";
	//for download jar
	//https://mvnrepository.com/artifact/mysql/mysql-connector-java/8.0.23
	public static void main(String[] args) {
		Scanner sc=null;
		String newSname=null,newSadd=null;
		float newAvg=0.0f;
		Connection con=null;
		PreparedStatement ps=null;
		int result =0;
		try {
			//raead inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Student Name");
				newSname=sc.next();
				
				System.out.println("Enter Student Address");
				newSadd=sc.next();
				
				System.out.println("Enter Student Average");
				newAvg=sc.nextFloat();
			}//if
			//register jdbc driver s/w
			
			//	Class.forName("oracle.jdbc.driver.OracleDriver");
			//	Class.forName("com.mysql.cj.jdbc.Driver");
			//established the connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","root");
			
		
			//create preparestatement obj
			if(con!=null)
				ps=con.prepareStatement(ORACLE_INSERT_QUERY);
			if(ps!=null) {
				ps.setInt(1,new Random().nextInt(100000)); // 0  to 9999
				ps.setString(2,newSname);
				ps.setString(3,newSadd);
				ps.setFloat(4,newAvg);
				result=ps.executeUpdate();
			}
			if(result==0)
				System.out.println("Renord not inserted");
			else
				System.out.println(result+"   Record are inserted");
					
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
