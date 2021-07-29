package com.fa.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import oracle.jdbc.internal.OracleTypes;

public class CsProcedureCursoreTest03 {
	private static final String CALL_PROCEDURE_QUERY="{ CALL P_GET_EMPDETAILS_BY_DESGS(?,?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		String desg1=null,desg2=null;
		Connection con=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		boolean flag=false;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter desg1");
				desg1=sc.next().toUpperCase();
				System.out.println("Enter desg2");
				desg2=sc.next().toUpperCase();
			}
			//register jdbc driver s/w
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//established the connectoin
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager");
			
			if(con!=null) 
				//creare CallableStatement object
				cs=con.prepareCall(CALL_PROCEDURE_QUERY);
				if(cs!=null) {
				//register OUT param with JDBC type
				cs.registerOutParameter(3, OracleTypes.CURSOR);
				//set values to in param
				cs.setString(1, desg1);
				cs.setString(2, desg2);
				//call PL/SQL procedure
				cs.execute();
				//gether results from OUT param
				rs=(ResultSet) cs.getObject(3);
				//process the ResultSet
				if(rs!=null) {
					while(rs.next()) {
						flag=true;
						System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"    "+rs.getString(3)+"    "+rs.getFloat(4));
					}//while
					if(flag==false)
						System.out.println("NO record found");
					else
						System.out.println("record found and display");
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
			//close jdbc objects
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
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
