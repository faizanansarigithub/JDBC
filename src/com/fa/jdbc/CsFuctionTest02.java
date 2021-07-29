package com.fa.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE FUNCTION FX_SELECT_BY_SNO 
(
  NO IN NUMBER 
, SNUMBER OUT NUMBER 
, STUDNAM OUT VARCHAR2 
, STUDADDRS OUT VARCHAR2 
) RETURN FLOAT AS 
    PERFORMANCE FLOAT;
BEGIN
    SELECT SNO,SNAME,SADD,AVG INTO SNUMBER,STUDNAM,STUDADDRS,performance FROM STUDENT WHERE SNO=NO;
  RETURN performance;
END FX_SELECT_BY_SNO;*/
public class CsFuctionTest02 {
	private static final String CALL_FUNCTION_QUERY="{?=call FX_SELECT_BY_SNO(?,?,?,?)}";

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cs=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter sno");
				no=sc.nextInt();
			}
			//established the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "manager");
			//create CallableStatement object
			if(con!=null)
				cs=con.prepareCall(CALL_FUNCTION_QUERY);
			//Register OUT param with JDBC Types
			if(cs!=null) {
				cs.registerOutParameter(1, Types.FLOAT);
				cs.registerOutParameter(3, Types.INTEGER);
				cs.registerOutParameter(4, Types.VARCHAR);
				cs.registerOutParameter(5, Types.VARCHAR);
			}
			//set IN param
			if(cs!=null)
				cs.setInt(2, no);
			//execute PL/SQL Function
			if(cs!=null)
				cs.execute();
			//Gather the results
			if(cs!=null) {
				System.out.println("Student Number "+cs.getInt(3));
				System.out.println("Student Name "+cs.getString(4));
				System.out.println("Student Address "+cs.getString(5));
				System.out.println("Student Avrage "+cs.getFloat(1));
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
