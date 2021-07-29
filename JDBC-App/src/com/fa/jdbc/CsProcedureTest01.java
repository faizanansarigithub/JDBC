package com.fa.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;



public class CsProcedureTest01 {
	private static final String CALL_PROCEDURE_QUERY="{  CALL P_ADDITION(?,?,?)}";

	public static void main(String[] args) {
		Scanner sc=null;
		int x=0,y=0;
		Connection con=null;
		CallableStatement cs=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter First values");
				x=sc.nextInt();
				System.out.println("Enter Second values");
				y=sc.nextInt();
			}
			//register jdbc driver s/w
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//established the connectoin
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager");
			//creare CallableStatement object
			if(con!=null)
				cs=con.prepareCall(CALL_PROCEDURE_QUERY);
			//register OUT params with JDBC type
			if(cs!=null)
				cs.registerOutParameter(3,Types.INTEGER);
			//set values to in param
			if(cs!=null) {
				cs.setInt(1, x);
				cs.setInt(2, y);
			}
			//call PL/SQL procedure
			if(cs!=null)
				cs.execute();
			//results from OUT param
		if(cs!=null)
			System.out.println("Sum is::"+cs.getInt(3));
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
