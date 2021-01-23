package com.fa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {
	
	public static void main(String[] args) {
		Scanner sc=null;
		String sname=null,sadd=null;
		float avg=0.0f;
		int sno=0;
		String query=null;
		Connection con=null;
		Statement st=null;
		int count=0;
		
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
			System.out.println("Enter student name");
			sname=sc.next();
			System.out.println("Enter student address");
			sadd=sc.next();
			System.out.println("Enter student avrg");
			avg=sc.nextFloat();
			System.out.println("Enter student existing sno");
			sno=sc.nextInt();
			}//if
			//convert input value as required for the SQL query 
			
			sname="'"+sname+"'";//   'shyam'
			sadd="'"+sadd+"'";//   'delhi'
			
			//register JDBC driver s/w
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//established the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "manager");
			//create JDBC statement object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL query
			//update student set sname='shyam',sadd='delhi',avg=77.77 where sno=20;
			query="UPDATE STUDENT SET SNAME="+sname+","+"sadd="+sadd+","+"avg="+avg+" where sno= "+sno;
			System.out.println(query);
			//send and execute SQL query in DB s/w
			if(st!=null)
				count=st.executeUpdate(query);//
			
			//process the result
			if(count==0)
				System.out.println("no records update");
			else
				System.out.println(count+" no. of records updated");
			
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


