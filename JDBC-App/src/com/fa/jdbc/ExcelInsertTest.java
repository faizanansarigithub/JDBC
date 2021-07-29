package com.fa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ExcelInsertTest {
	private static final String EXCEL_INSERT="INSERT INTO STUDENT.SHEET1 VALUES(?,?,?,?)";

	public static void main(String[] args) {
		Scanner sc=null;
		int sno=0;
		String name=null,sadd=null;
		float avg=0.0f;
		int count=0;
		try {
			sc=new Scanner(System.in);
			
			System.out.println("Enter sno");
			sno=sc.nextInt();
			System.out.println("Enter s name");
			name=sc.next();
			System.out.println("Enter s address");
			sadd=sc.next();
			System.out.println("enter s avg");
			avg=sc.nextFloat();
		}//
		catch(Exception e) {
			e.printStackTrace();
		}
	try(Connection con=DriverManager.getConnection("jdbc:Excel:///F:/workspace/JDBCCLass")) {
		try(PreparedStatement ps=con.prepareStatement(EXCEL_INSERT)) {
			//set values to thequery param
			ps.setInt(1,sno);ps.setString(2, name);ps.setString(3,sadd );ps.setFloat(4, avg);
			count=ps.executeUpdate();
			//process the result
			if(count==0)
				System.out.println("record not inserted");
			else
				System.out.println("records are inserted");
			
		}//try2
	}//try1
	catch(SQLException se) {
		se.printStackTrace();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	}

}
