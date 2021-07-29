package com.fa.jdbc;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class NaukriTestBLOBAndCLOBWithOracle {
	private static final String NAUKRI_INSERT_QUERY = "INSERT INTO NAUKRI_INFO VALUES(NAUKRI_INFO_SEQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?)";

	public static void main(String[] args) {
		Scanner sc=null;
		String name=null,addrs=null,email=null,gender=null,dob=null,resume=null,photo=null,txt=null;
		long mobile=0L;
		float age=0.0f;
		Connection con=null;
		PreparedStatement ps=null;
		InputStream photoIS=null,resumeIS=null,txtIS=null;
		SimpleDateFormat sdf=null;
		java.util.Date udate=null;
		java.sql.Date sdate=null;
		Reader reader=null;
		int result=0;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Your Name");
				name=sc.next();
				System.out.println("Enter Your Gender Male or Female");
				gender=sc.next();
				System.out.println("Enter Your DOB dd-MM-yyyy");
				dob=sc.next();
				System.out.println("Enter Your Addrs");
				addrs=sc.next();
				System.out.println("Enter Your E-mail ID");
				email=sc.next();
				System.out.println("Enter Your Mobile Number");
				mobile=sc.nextLong();
				System.out.println("Enter Your Age");
				age=sc.nextFloat();
				System.out.println("Select your Reume Location path ");
				resume=sc.next();
				System.out.println("Select your photo Location path ");
				photo=sc.next();
				System.out.println("Select your Text file Location path ");
				txt=sc.next();
				
			}
			//converting String Date values to java.util.Date class objs
			sdf=new SimpleDateFormat("dd-MM-yyyy");
			if(sdf!=null)
				udate=sdf.parse(dob);
			//converting java.util.Date class objects to java.sql.Date
			if(udate!=null)
				sdate=new java.sql.Date(udate.getTime());
			//convert inputStrem
			resumeIS	=new FileInputStream(resume);
			photoIS		=new FileInputStream(photo);
			reader=new FileReader(txt);
			//txtIS       		= new FileInputStream(txt);
			//load the db s/w
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "manager");
			//create preparedStatement object
			if(con!=null)
				ps=con.prepareStatement(NAUKRI_INSERT_QUERY);
			//set param query
			if(ps!=null) {
				ps.setString(1, name);
				ps.setString(2,gender);
				ps.setDate(3, sdate);
				ps.setString(4,addrs);
				ps.setString(5, email);
				ps.setLong(6, mobile);
				ps.setFloat(7, age);
				ps.setBinaryStream(8, resumeIS);
				ps.setBinaryStream(9,photoIS);
				ps.setCharacterStream(10, reader);
				
			}
			//execute query
			if(ps!=null)
				result=ps.executeUpdate();
			//process the result
			if(result==0)
				System.out.println("values not inserted");
			else
				System.out.println("Registration Successfully");
			}
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		finally {
					
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (sc != null)
					sc.close();
			} catch (Exception se) {
				se.printStackTrace();
			}
		} // finally
	}
}
