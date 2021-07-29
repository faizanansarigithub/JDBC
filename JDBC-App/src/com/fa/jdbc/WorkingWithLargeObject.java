package com.fa.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.proxy.annotation.Pre;

public class WorkingWithLargeObject {
 private static final String ARTIST_INSERT_QUERY="INSERT INTO ARTIST_INFO(ARTISTNAME,ARTISTADDRS,INCOME,PHOTO,VIDEO) VALUES(?,?,?,?,?)";
	public static void main(String[] args) {
		Scanner sc=null;
		String name=null,addrs=null,photoLocation=null,videoLocation=null;
		float income=0.0f;
		InputStream photoIS=null,videoIS=null;
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Artist name::");
				name=sc.next();
				System.out.println("Enter Artist Address::");
				addrs=sc.next();
				System.out.println("Enter Artist income::");
				income=sc.nextFloat();
				System.out.println("Enter Artist photo path::");
				photoLocation=sc.next();
				System.out.println("Enter Artist video path::");
				videoLocation=sc.next();
			}//if
			//create input stream representing photo file and video file
			
			photoIS =new FileInputStream(photoLocation);
			videoIS =new FileInputStream(videoLocation);
			//register jdbc driver s/w
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//established the connection
			//con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","root");
			//create PreparedStatement obj
			if(con!=null)
				ps=con.prepareStatement(ARTIST_INSERT_QUERY);
			//set values to query params
			if(ps!=null) {
				ps.setString(1, name);
				ps.setString(2,addrs);
				ps.setFloat(3, income);
				ps.setBlob(4, photoIS);
				ps.setBlob(5, videoIS);
			}
			//execute the query
			if(ps!=null)
				result=ps.executeUpdate();
			//process the result
			if(result==0)
				System.out.println("Record not inserted");
			else
				System.out.println("record inserted");
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
			catch(Exception se) {
				se.printStackTrace();
			}
			
			try {
				if(photoIS!=null)
					photoIS.close();
			}
			catch(Exception se) {
				se.printStackTrace();
			}
			
			try {
				if(videoIS!=null)
					videoIS.close();
			}
			catch(Exception se) {
				se.printStackTrace();
			}
			
			
		}//finally
	}

}
