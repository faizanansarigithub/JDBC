package com.fa.jdbc;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class PsBLOBRetriveUsingOracle {
	private static final String SELECT_QUERY_NAME="SELECT * FROM ARTIST_INFO WHERE ARTISTNAME=?";
	//https://mvnrepository.com/artifact/commons-io/commons-io/2.4
	public static void main(String[] args) {
		Scanner sc=null;
		String name=null,artName=null,addrs=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int sno=0;
		float income=0.0f;
		InputStream photoIS=null, videoIS=null;
		OutputStream photoOS=null,videoOS=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null)
			{
				System.out.println("Enter Artist Name");
				name=sc.next();
			}
			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager");
			//create prepare statemtn obje
			if(con!=null)
				ps=con.prepareStatement(SELECT_QUERY_NAME);
			//set query para
			if(ps!=null) {
				ps.setString(1,name);
			}
			//execute query
			if(ps!=null)
				rs=ps.executeQuery();
			//prosecc the result set
			if(rs!=null) {
				if(rs.next()) {
					sno=rs.getInt(1);
					artName=rs.getString(2);
					addrs=rs.getString(3);
					income=rs.getFloat(4);
					photoIS=rs.getBinaryStream(5);
					videoIS=rs.getBinaryStream(6);
					System.out.println(sno+"  "+artName+"   "+addrs+"   "+income);
					photoOS=new FileOutputStream("f:/CoreJava/monu.jpg");
					videoOS=new FileOutputStream("f:/CoreJava/sonu.mp4");	
					//copy input streame to outputstream
					if(photoIS!=null && videoIS!=null) {
						IOUtils.copy(photoIS, photoOS);
						IOUtils.copy(videoIS, videoOS);
					}
					System.out.println("BLOB  values are retrive");
				}
				else {
					System.out.println("REcord not found");
				}
			}
			
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
		
	}//main

}//class
