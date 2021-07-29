package com.fa.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsProcedureTest01 {
	private static final String CALL_PROCEDURE_QUERY="{ CALL P_GET_STUDENTDETAILS_BY_NO(?,?,?,?) }";

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		int no=0;
		CallableStatement cs=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Student no");
				no=sc.nextInt();
			}//if
			//established the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager");
			//create CallableStatemetn
			if(con!=null)
				cs=con.prepareCall(CALL_PROCEDURE_QUERY);
			//register OUT param with JDBC types
			if(cs!=null) {
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.registerOutParameter(4, Types.FLOAT);
			}
			//set value to IN param
			if(cs!=null)
				cs.setInt(1, no);
			//execute PL/SQL procedure
			if(cs!=null)
				cs.execute();
			//gather results from OUT params
			if(cs!=null) {
				System.out.println("Student Name is:: "+cs.getString(2));
				System.out.println("Student Address is:: "+cs.getString(3));
				System.out.println("Student Average is:: "+cs.getFloat(4));
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
