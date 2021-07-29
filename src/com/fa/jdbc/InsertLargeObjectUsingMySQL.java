package com.fa.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertLargeObjectUsingMySQL {
	private static final String INSERT_QUERY="insert into basic(name,addrs,photo)values(?,?,?)";

	public static void main(String[] args) {
		Scanner sc=null;
		String name=null,addrs=null,photopath=null;
		InputStream photoIS=null;
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Name");
				name=sc.next();
				System.out.println("Enter Address");
				addrs=sc.next();
				System.out.println("Enter phtopath");
				photopath=sc.next();
			}
			//inputStream 
			photoIS=new FileInputStream(photopath);
			//establish the connection
			//register jdbc driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/jdbc","root","root");
			//create prepareStatement object
			if(con!=null)
				ps=con.prepareStatement(INSERT_QUERY);
			//set query param
			if(ps!=null) {
				ps.setString(1, name);
				ps.setString(2, addrs);
				ps.setBlob(3, photoIS);
			}
			//execute query
			if(ps!=null)
				result=ps.executeUpdate();
			//set result
			if(result==0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record inserted");
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(ClassNotFoundException sne) {
			sne.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
