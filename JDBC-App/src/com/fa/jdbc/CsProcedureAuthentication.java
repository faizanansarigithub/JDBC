package com.fa.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsProcedureAuthentication {
	private static final String CALL_PROCEDURE_QUERY="{ CALL P_USERAUTHENTICATION(?,?,?)}";

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
			//register jdbc driver s/w
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//established the connectoin
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager");
			//creare CallableStatement object
			if(con!=null)
				cs=con.prepareCall(CALL_PROCEDURE_QUERY);
			//register OUT param with JDBC type
			if(cs!=null)
				cs.registerOutParameter(3, Types.VARCHAR);
			//set in param
			if(cs!=null)
			{
				cs.setString(1, userName);
				cs.setString(2, password);
			}
			//execute PL/SQL procedure
			if(cs!=null)
				cs.execute();
			//gether the results from OUT pram
			if(cs!=null) {
				System.out.println("Result "+cs.getString(3));
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
