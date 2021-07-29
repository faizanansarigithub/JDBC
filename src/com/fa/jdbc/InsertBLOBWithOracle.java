package com.fa.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertBLOBWithOracle {
	private static final String INSERT_DULHA_DETAIL = "INSERT INTO SHADI VALUES (?,?,?,?,?)";

	public static void main(String[] args) {
		Scanner sc = null;
		String dname = null, daddrs = null, photopath = null, videopath = null;
		float dincome = 0.0f;
		InputStream photoIS = null, videoIS = null;
		Connection con = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			sc = new Scanner(System.in);
			if (sc != null) {
				System.out.println("Enter Dulha Name");
				dname = sc.nextLine();
				System.out.println("Enter Dulha Address");
				daddrs = sc.next();
				System.out.println("Enter Dulha salary income");
				dincome = sc.nextFloat();
				System.out.println("Enter photo path");
				photopath = sc.next();
				System.out.println("Enter video path");
				videopath = sc.next();
			} // if
				// create inputStream representing photo file and video file
			photoIS = new FileInputStream(photopath);
			videoIS = new FileInputStream(videopath);
			// establish the connection
			
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager");
			// create preparedStatement object
			if (con != null)
				ps = con.prepareStatement(INSERT_DULHA_DETAIL);
			// set value to query param
			if (ps != null) {
				ps.setString(1, dname);
				ps.setString(2, daddrs);
				ps.setFloat(3, dincome);
				ps.setBlob(4, photoIS);
				ps.setBlob(5, videoIS);
			}
			// execute the query
			if (ps != null)
				result = ps.executeUpdate();
			// process the result
			if (result == 0)
				System.out.println("Registration failed!!!!!!!!!!!!!!!! ");
			else
				System.out.println("Registarion Successfull AAP Dulha JARUR banege");
		} // try
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

			try {
				if (photoIS != null)
					photoIS.close();
			} catch (Exception se) {
				se.printStackTrace();
			}
			try {
				if (videoIS != null)
					videoIS.close();
			} catch (Exception se) {
				se.printStackTrace();
			}
		} // finally
	}//main
}//class
