package com.fa.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsProcedureAuthentication {
	private static final String CALL_AUTHENTICATION_QUERY="{ CALL P_AUTHENTICATION(?,?,?)}";

	public static void main(String[] args) {
		Scanner sc=null;
		String userName=null,password=null;
		Connection con=null;
		CallableStatement cs=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter User Name");
				userName=sc.next();
				System.out.println("Enter Password");
				password=sc.next();
			}
			//established the connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager");
			//create CallableStatement object
			if(con!=null)
				cs=con.prepareCall(CALL_AUTHENTICATION_QUERY);
			//register OUT param value with JDBC types
			if(cs!=null) {
				cs.registerOutParameter(3,Types.VARCHAR);
			}
			//set value to IN param
			if(cs!=null) {
				cs.setString(1, userName);
				cs.setString(2, password);
			}
			//execute call PL/SQL procedure
			if(cs!=null)
				cs.execute();
			//gether the result from OUT param
			if(cs!=null)
				System.out.println("Result is "+cs.getString(3));
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
