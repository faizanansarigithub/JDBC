package com.fa.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
	/*CREATE OR REPLACE FUNCTION FX_GET_EMPLOYEE_BY_NAME 
	(
	  NAME IN VARCHAR2 
	, ENO OUT NUMBER 
	, EMPNAME OUT VARCHAR2 
	, JOB OUT VARCHAR2 
	, EMPSALARY OUT FLOAT 
	) RETURN NUMBER AS 
	    depnumber number;
	BEGIN
	    select empno,ename,job,sal,deptno into eno,empname,job,empsalary,depnumber from emp where ename=name;
	  RETURN depnumber;
	END FX_GET_EMPLOYEE_BY_NAME;*/

public class CsFuntionTest03 {
	private static final String CALL_FUNCTION_QUERY="{?=call FX_GET_EMPLOYEE_BY_NAME(?,?,?,?,?)}";

	public static void main(String[] args) {
		Scanner sc=null;
		String name=null;
		Connection con=null;
		CallableStatement cs=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Employee Name");
				name=sc.next().toUpperCase();
			}
			//established the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "manager");
			//create CallableStatement object
			if(con!=null)
				cs=con.prepareCall(CALL_FUNCTION_QUERY);
			//Register OUT param with JDBC Types
			if(cs!=null) {
				cs.registerOutParameter(1, Types.INTEGER);
				cs.registerOutParameter(3, Types.INTEGER);
				cs.registerOutParameter(4, Types.VARCHAR);
				cs.registerOutParameter(5, Types.VARCHAR);
				cs.registerOutParameter(6, Types.FLOAT);
			}
			//set IN param
			if(cs!=null)
				cs.setString(2, name);
			//execute PL/SQL Function
			if(cs!=null)
				cs.execute();
			//Gether the results
			if(cs!=null) {
				System.out.println("Employee Nnumber "+cs.getInt(3));
				System.out.println("Employee Name "+cs.getString(4));
				System.out.println("Employee Job "+cs.getString(5));
				System.out.println("Employee salary"+cs.getFloat(6));
				System.out.println("Employee department "+cs.getInt(1));
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(cs!=null)
					cs.close();
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
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main
}//class
