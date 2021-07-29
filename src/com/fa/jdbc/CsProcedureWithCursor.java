package com.fa.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import oracle.jdbc.internal.OracleTypes;

		/*CREATE OR REPLACE PROCEDURE P_GET_EMPDESG_BY_ENPNO 
		(
		  ENO1 IN NUMBER 
		, ENO2 IN NUMBER 
		, EMPDETAILS OUT SYS_REFCURSOR 
		) AS 
		BEGIN
		  OPEN EMPDETAILS FOR
		  SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE EMPNO IN(ENO1,ENO2);
		END P_GET_EMPDESG_BY_ENPNO;*/

public class CsProcedureWithCursor {
	private final static  String  CALL_PROCEDURE_QUERY="{ CALL P_GET_EMPDESG_BY_ENPNO(?,?,?)}";

	public static void main(String[] args) {
		Scanner sc=null;
		int empno1=0,empno2=0;
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		boolean flag=false;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter your First Employee Number");
				empno1=sc.nextInt();
				System.out.println("Enter your Second Employee Number");
				empno2=sc.nextInt();
			}//if
			//established the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system","manager");
			//create CallableStatament object
			if(con!=null)
				cs=con.prepareCall(CALL_PROCEDURE_QUERY);
			//Register OUT param with JDBC type
			if(cs!=null)
				cs.registerOutParameter(3, OracleTypes.CURSOR);
			//set IN param
			if(cs!=null) {
				cs.setInt(1, empno1);
				cs.setInt(2, empno2);
			}
			//execute query
			if(cs!=null)
				cs.execute();
			//gather the result
			if(cs!=null) {
				rs=(ResultSet) cs.getObject(3);
				if(rs!=null) {
					while(rs.next()) {
						flag=true;
						System.out.println(rs.getInt(1)+"       "+rs.getString(2)+"       "+rs.getString(3)+"      "+rs.getFloat(4)+"  "+rs.getInt(5));
					}//while
					if(flag)
						System.out.println("Record Found and display");
					else
						System.out.println("Record not found");
				}//if
			}//if
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
