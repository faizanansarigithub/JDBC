package com.fa.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsProcedureTest02 {
	private static final String SELECT_PROCEDURE_QUERY="{  CALL P_GET_DEPARMENT_NO(?,?,?) }";

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cs=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Deparment Number");
				no=sc.nextInt();
			}
			//established the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system","manager");
			//create CallableStatament object
			if(con!=null)
				cs=con.prepareCall(SELECT_PROCEDURE_QUERY);
			//Register OUT param 
			if(cs!=null) {
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.VARCHAR);
			}
			//set IN param
			if(cs!=null)
				cs.setInt(1, no);
			//execute PL/SQL procedure
			if(cs!=null)
			cs.execute();
			//gether the result
			if(cs!=null) {
				System.out.println(cs.getString(2));
				System.out.println(cs.getString(3));
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
	}

}
