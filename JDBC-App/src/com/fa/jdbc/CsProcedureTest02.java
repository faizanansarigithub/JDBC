package com.fa.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CsProcedureTest02 {
	private static final String CALL_PROCEDURE_QUERY="CALL P_GETEMPLOYEE(?,?,?,?) ";

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cs=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter employee number");
				no=sc.nextInt();
			}
			//register jdbc driver s/w
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//established the connectoin
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager");
			//creare CallableStatement object
			if(con!=null)
				cs=con.prepareCall(CALL_PROCEDURE_QUERY);
			//register OUT param with JDBC type
			if(cs!=null) {
				cs.registerOutParameter(2, Types.VARCHAR);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.registerOutParameter(4, Types.FLOAT);
			}
			//set value to IN params
			if(cs!=null)
				cs.setInt(1, no);
			//execute PL/SQL procedure
			if(cs!=null)
				cs.execute();
			//gether results from OUT param
			if(cs!=null) {
				System.out.println("emp Name"+cs.getString(2));
				System.out.println("emp desg"+cs.getString(3));
				System.out.println("emp salary"+cs.getFloat(4));
			}
		}
		catch(SQLException se) {
			if(se.getErrorCode()==1403)
				System.out.println("Enter employee number is invalid");
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
