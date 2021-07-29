package com.fa.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.internal.OracleTypes;

/*CREATE OR REPLACE PROCEDURE P_GETEMPLOYEE_BY_DEPTNO 
(
  DEPT1 IN NUMBER 
, DEPT2 IN NUMBER 
, RESULT OUT SYS_REFCURSOR 
) AS 
BEGIN
 open result for
    select empno,ename,job,sal from emp where deptno in(dept1,dept2);
END P_GETEMPLOYEE_BY_DEPTNO;*/

public class CsProcedureCursorTest02 {
	private static final String CALL_PROCEDURE_QUERY="{CALL P_GETEMPLOYEE_BY_DEPTNO(?,?,?)}";

	public static void main(String[] args) {
		Scanner sc=null;
		int deptno1=0,deptno2=0;
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		boolean flag=false;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter First Department Number");
				deptno1=sc.nextInt();
				System.out.println("Enter Second Department Number");
				deptno2=sc.nextInt();
			}
			//established the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system", "manager");
			//create CallableStatement object
			if(con!=null)
				cs=con.prepareCall(CALL_PROCEDURE_QUERY);
			//Register OUT param With JDBC types
			if(cs!=null) 
				cs.registerOutParameter(3, OracleTypes.CURSOR);
			//set IN param
			if(cs!=null) {
				cs.setInt(1, deptno1);
				cs.setInt(2, deptno2);
			}
			//excute PL/SQL Procedure
			cs.execute();
			//gether the result
			rs=(ResultSet) cs.getObject(3);
			//process the ResultSet
			if(rs!=null) {
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+"      "+rs.getString(2)+"       "+rs.getString(3)+"     "+rs.getFloat(4));
				}
				if(flag)
					System.out.println("Record Found and Display");
				else
					System.out.println("Record Not Found ");
			}
		}//try
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
