package com.fa.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE FUNCTION FX_GET_EMPLOYEE_BY_ENO 
	(
	  ENO IN NUMBER 
	, EMPNUMBER OUT NUMBER 
	, EMPNAME OUT VARCHAR2 
	, EMPJOB OUT VARCHAR2 
	, EMPDEPTNO OUT NUMBER 
	) RETURN FLOAT AS 
	    salary float;
	BEGIN
	    select empno,ename,job,sal,deptno into empnumber,empname,empjob,salary,empdeptno from emp where empno=eno;
	  RETURN salary;
	END FX_GET_EMPLOYEE_BY_ENO;*/

public class CsFunctionTest01 {
	private static final String CALL_FUNCTION_QUERY="{ ?= call FX_GET_EMPLOYEE_BY_ENO(?,?,?,?,?)}";

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cs=null;
		try{
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter emp no");
				no=sc.nextInt();
			}
			//established the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system", "manager");
			//create CallableStatement object
			if(con!=null)
				cs=con.prepareCall(CALL_FUNCTION_QUERY);
			//register OUT param with JDBC types
			if(cs!=null) {
				cs.registerOutParameter(1, Types.FLOAT);
				cs.registerOutParameter(3, Types.INTEGER);
				cs.registerOutParameter(4, Types.VARCHAR);
				cs.registerOutParameter(5, Types.VARCHAR);
				cs.registerOutParameter(6, Types.INTEGER);
			}//if
			//set IN param
			if(cs!=null)
				cs.setInt(2, no);
			if(cs!=null)
				cs.execute();
			//gather the result
			if(cs!=null) {
				System.out.println("employee Number "+cs.getInt(3));
				System.out.println("employee Name "+cs.getString(4));
				System.out.println("employee job "+cs.getString(5));
				System.out.println("employee deptno "+cs.getInt(6));
				System.out.println("employee salary "+cs.getInt(1));
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close jdbc objects
			
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
