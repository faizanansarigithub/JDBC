package com.fa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest {
	
	public static void main(String[] args) {
		
		Scanner sc=null;
		int sno=0;
		String sname=null,sadd=null;
		float avg=0.0f;
		Connection con=null;
		Statement st=null;
		String query=null;
		int count=0;
		
		try {
			//read inputs
			sc= new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Student number::");
				sno=sc.nextInt(); //gives 101
				System.out.println("Enter student name::");
				sname=sc.next();  //ramesh
				System.out.println("Enter student Address::");
				sadd=sc.next();		//delhi
				System.out.println("Enter student avg::");
				avg=sc.nextFloat();	//90.98
			}//if
			//convert input values as required for the SQL Query
			
			sname="'"+sname+"'";// 'ramesh'
			sadd="'"+sadd+"'";// 'delhi'
			//register jdbc driver s/w
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//establish the connection
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system", "manager");
			//create statement
			if(con!=null)
				st=con.createStatement();
			//prepare SQL Query
			//insert into student values(101,'ramesh','delhi',90.98);
			query="INSERT INTO STUDENT VALUES"+sno+","+sname+","+sadd+","+avg+")";
			System.out.println(query);
			//send and execute SQL query in DB s/w
			if(st!=null)
				count=st.executeUpdate(query);
			//process the result
			if(count==0)
				System.out.println("record not inserted");
			else
				System.out.println(count+" Record inserted");
		}//try
		catch(SQLException se) {
			
			if(se.getErrorCode()==12899) {
				System.out.println("you can not insert value more than 20 charecters");
			}
			if(se.getErrorCode()==1) {
				System.out.println("you can not insert duplicates value in sno");
			}
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(con!=null)
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(sc!=null)
				sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main

}//class
