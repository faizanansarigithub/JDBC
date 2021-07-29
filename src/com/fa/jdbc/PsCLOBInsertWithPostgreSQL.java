package com.fa.jdbc;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsCLOBInsertWithPostgreSQL {
	private static final String INSERT_QUERY_NAUKRI="INSERT INTO NAUKRI_INFO VALUES(nextval('NAUKRI_INFO_SEQ'),?,?,?)";

	public static void main(String[] args) {
		Scanner sc= null;
		String name=null,addrs=null,resumePath=null;
		Connection con=null;
		PreparedStatement ps=null;
		Reader reader=null;
		int result=0;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter your Name");
				name=sc.next();
				System.out.println("Enter your Addrs");
				addrs=sc.next();
				System.out.println("Enter your Resume Location");
				resumePath=sc.next();
			}//if
			//establish the connection
			con=DriverManager.getConnection("jdbc:postgresql:jdbc01","postgres","root");
			//create reader stream(Charecter stream ) representing text file data
			reader=new FileReader(resumePath);
			//create preparedStatement object
			if(con!=null)
				ps=con.prepareStatement(INSERT_QUERY_NAUKRI);
			//set value in pram
			if(ps!=null) {
				ps.setString(1, name);
				ps.setString(2, addrs);
				ps.setCharacterStream(3, reader);
			}
			//execute the query
			if(ps!=null)
				result=ps.executeUpdate();
			//process the result
			if(result==0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record inserted");
		}//try
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
	}//main
}//class
