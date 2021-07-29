package com.fa.jdbc;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsCLOBInsertWithPostgresql {
	private static final String INSERT_QUERY_CLOB = "INSERT INTO NAUKRI_INFO VALUES(nextval('naukri_info_seq'),?,?,?,?)";

	public static void main(String[] args) {
		Scanner sc = null;
		int regno = 0;
		String name = null, addrs = null, qly = null, resumeLocation = null;
		Connection con = null;
		PreparedStatement ps = null;
		Reader reader = null;
		int result = 0;
		try {
			sc = new Scanner(System.in);
			if (sc != null) {
				System.out.println("Enter your Name");
				name = sc.next();
				System.out.println("Enter your Address");
				addrs = sc.next();
				System.out.println("Enter your Qualification");
				qly = sc.next();
				System.out.println("Enter your Resume Location");
				resumeLocation = sc.next();

			} // if
				// register jdbc driver s/w
				// Class.forName("org.postgresql.Driver");
				// established the connection
			con = DriverManager.getConnection("jdbc:postgresql:jdbc_classes", "postgres", "root");
			// create preparestament obje
			reader = new FileReader(resumeLocation);
			if (con != null)
				ps = con.prepareStatement(INSERT_QUERY_CLOB);
			// set values in param
			if (ps != null) {
				ps.setString(1, name);
				ps.setString(2, addrs);
				ps.setString(3, qly);
				ps.setCharacterStream(4, reader);
			}
			// execute query
			if (ps != null)
				result = ps.executeUpdate();
			// process the result
			if (result == 0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record inserted");
		} // try

		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close jdbc objects
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
				if (reader != null)
					reader.close();
			} catch (Exception se) {
				se.printStackTrace();
			}
		} // finally
	}// main
}// class
