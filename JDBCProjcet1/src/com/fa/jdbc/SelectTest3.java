package com.fa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class SelectTest3 {

	public static void main(String[] args) {
		System.out.println("SelectTest3");
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int no=0;
		String query=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Employee number");
				no=sc.nextInt();
			}//if
			//register jdbc driver s/w(Optionl)
			//Class.forName("oracle.jdbc.driver.OracleDriver");

			//establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager");
			if(con!=null)
				st=con.createStatement();
			//prepare SQL Query
			//select empno, ename , job , sal from emp where empno=7369;
			query="SELECT EMPNO, ENAME , JOB , SAL FROM EMP WHERE EMPNO="+no;
			//send and execute query
			if(st!=null)
				rs=st.executeQuery(query);
			
			//process the ResultSet obj
			if(rs!=null) {
				if(rs.next())
					System.out.println(rs.getString(1)+"  "+rs.getString(2)+ "  "+rs.getString(3)+" "+rs.getString(4));
				else
					System.out.println("No records found");
			}//if
			
		}//try
		catch (SQLException se) {//known exception
			se.printStackTrace();
		}
		catch (Exception e) {//Unknown exception
			e.printStackTrace();
		}
		finally {
			//close jdbc objs
			try {
				if(rs!=null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(st!=null)
					st.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(con!=null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			
			try {
				if(sc!=null)
					sc.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//finally

	}//main

}//class
