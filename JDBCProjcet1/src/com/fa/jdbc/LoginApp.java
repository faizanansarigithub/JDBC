package com.fa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp {

	public static void main(String[] args) {
		Scanner sc=null;
		String user=null;
		String pass=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		int count=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter userName:::");
				user=sc.nextLine();//gives sona
				System.out.println("Enter Password:::");
				pass=sc.nextLine();//gives babu
			}//if
			//convert input values as required for the SQL query
			user="'"+user+"'";//gives 'sona'
			pass="'"+pass+"'";//gives 'babu'
			//register JDBC s/w
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager");
			//create statement object
			if(con!=null)
				st=con.createStatement();
			//prepare SQL Query
			// select count(*) from userInfo where uname='sonasss' and pwd='babu';
			query= "SELECT COUNT(*) FROM USERINFO WHERE UNAME="+user+" and pwd="+pass;
			System.out.println(query);
			//send and excute SQL
			if(st!=null)
				rs=st.executeQuery(query);
			//process the result
			if(rs!=null) {
				rs.next();
				count=rs.getInt(1);
			}
			if(count==0)
				System.out.println("invalid user or password");
			else
				System.out.println("Valid user name");
			
				
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
