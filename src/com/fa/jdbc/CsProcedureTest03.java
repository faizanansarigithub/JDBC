package com.fa.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;
/*CREATE OR REPLACE PROCEDURE P_GET_EMPLOYEE_BY_NAME 
(
  NAME IN VARCHAR2 
, ENO OUT NUMBER 
, EMPNAME OUT VARCHAR2 
, EJOB OUT VARCHAR2 
, SALARY OUT FLOAT 
) AS 
BEGIN
  select empno,ename,job,sal into eno,empname,ejob,salary from emp where ename=name;
END P_GET_EMPLOYEE_BY_NAME;*/

public class CsProcedureTest03 {
	private static final String CALL_PROCEDURE_QUERY="{ CALL P_GET_EMPLOYEE_BY_NAME(?,?,?,?,?)}";

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
				cs=con.prepareCall(CALL_PROCEDURE_QUERY);
			//Register OUT param with JDBC Types
			if(cs!=null) {
				cs.registerOutParameter(2, Types.INTEGER);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.registerOutParameter(4, Types.VARCHAR);
				cs.registerOutParameter(5, Types.FLOAT);
			}
			//set IN param
			if(cs!=null)
				cs.setString(1, name);
			//execute query
			if(cs!=null)
				cs.execute();
			//gether the results
			if(cs!=null) {
				System.out.println("Employee Number "+cs.getInt(2));
				System.out.println("Employee Name "+cs.getString(3));
				System.out.println("Employee Job "+cs.getString(4));
				System.out.println("Employee Salary "+cs.getFloat(5));
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
