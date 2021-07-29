package com.fa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertUsingMySQL {
	//INSERT INTO `insertdetails`(`firstname`, `secondname`, `fathername`) VALUES ([value-1],[value-2],[value-3])
	private static final String MYSQL_INSERT_QUERY="INSERT INTO `insertdetails`(`firstname`, `secondname`, `fathername`) VALUES (?,?,?)";
	

	public static void main(String[] args) {
		Scanner sc=null;
		String fname=null,sname=null,fathersname=null;
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				
				System.out.println("Enter First Name");
				fname=sc.next();
				System.out.println("Enter Second Name");
				sname=sc.next();
				System.out.println("Enter Fathers Name");
				fathersname=sc.next();
			}//if
			//Register MySQL database
			//Class.forName("com.mysql.cj.jdbc.Driver");
			//establish the connection
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/registration", "root","");
			//create preparedStatemetn object
			if(con!=null)
				ps=con.prepareStatement(MYSQL_INSERT_QUERY);
			if(ps!=null) {
				ps.setString(1, fname);
				ps.setString(2, sname);
				ps.setString(3, fathersname);
				result=ps.executeUpdate();
			}
			if(result==0)
				System.out.println("Record not insertde");
			else
				System.out.println("Records are inserted");
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
