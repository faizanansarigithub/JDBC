package com.fa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateInsert {
  //private static final String DATE_INSERT_QUERY="INSERT INTO PERSON_DATE_TAB VALUES(PERSON_PID_SEQ.NEXTVAL,?,?,?,?,?)";
  private static final String DATE_INSERT_QUERY="INSERT INTO PERSON_DATE_TAB(PNAME,PADDRS,DOB,DOM,DOJ) VALUES(?,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		String name=null,addrs=null,sdob=null,sdom=null,sdoj=null;
		SimpleDateFormat sdf1=null,sdf2=null;
		java.util.Date udob=null,udom=null,udoj=null;
		java.sql.Date sqdob=null,sqdom=null,sqdoj=null;
		Connection con=null;
		PreparedStatement ps=null;
		int count=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter person Name");
				name=sc.next();
				System.out.println("Enter person Address");
				addrs=sc.next();
				System.out.println("Enter person DOB(dd-MM-yyyy):");
				sdob=sc.next();
				System.out.println("Enter person DOM(MM-dd-yyyy):");
				sdom=sc.next();
				System.out.println("Enter person DOJ(yyyy-MM-dd):");
				sdoj=sc.next();
				
			}//if
			//convert string Date vlues to java.util.Date class objects (dod,dom)
			sdf1=new SimpleDateFormat("dd-MM-yyyy");
			udob=sdf1.parse(sdob);
			
			sdf2=new SimpleDateFormat("MM-dd-yyyy");
			udom=sdf2.parse(sdom);
			
			//conver java.util.Date class object to java.sql.Date class objects(dob,bom)
			if(udob!=null)
			sqdob=new java.sql.Date(udob.getTime());
			if(udom!=null)
				sqdom=new java.sql.Date(udom.getTime());
			//convert String date values (yyyy-MM-dd) (DOJ) direct to java.sql.Date clsss object
			
			sqdoj=java.sql.Date.valueOf(sdoj);
			
			//Register jdbc driver s/w
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			//con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "manager");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "root");
			//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","root");
			//create PreparedStatemetn object
			if(con!=null)
				ps=con.prepareStatement(DATE_INSERT_QUERY);
			//set values to query param
			if(ps!=null) {
				ps.setString(1, name);
				ps.setString(2, addrs);
				ps.setDate(3, sqdob);
				ps.setDate(4, sqdom);
				ps.setDate(5, sqdoj);
				
				//execute the SQL query
				count=ps.executeUpdate();
			}
			//process the result
			if(count==0)
				System.out.println("Record not inserted");
			else
				System.out.println("record insertd");
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


	}

}
