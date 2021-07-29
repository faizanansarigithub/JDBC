package com.fa.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*CREATE OR REPLACE FUNCTION FX_SELECT_EMP_BY_ENO 
(
  ENO IN NUMBER 
, NAME OUT VARCHAR2 
, EJOB OUT VARCHAR2 
) RETURN NUMBER AS 
    SALARY FLOAT;
BEGIN
    SELECT ENAME,JOB,SAL INTO NAME,EJOB,SALARY FROM EMP WHERE EMPNO=ENO;

  RETURN SALARY;
END FX_SELECT_EMP_BY_ENO;*/

public class CsFunctionTest01 {
	private static final String CALL_FUNCTION_QUERY="{?= call FX_SELECT_EMP_BY_ENO(?,?,?)}";

	public static void main(String[] args) {
		Scanner sc=null;
		int no=0;
		Connection con=null;
		CallableStatement cs=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Employee Number");
				no=sc.nextInt();
			}
			//established the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "manager");
			//create callableStatement object
			if(con!=null)
				cs=con.prepareCall(CALL_FUNCTION_QUERY);
			//register OUT param with JDBC types
			if(cs!=null) {
				cs.registerOutParameter(1, Types.FLOAT);
				cs.registerOutParameter(3, Types.VARCHAR);
				cs.registerOutParameter(4, Types.VARCHAR);
			}
			//set IN param 
			if(cs!=null)
				cs.setInt(2, no);
			//execute PL/SQL function
			cs.execute();
			//gether the results
			if(cs!=null) {
				System.out.println("Employee name"+cs.getString(3));
				System.out.println("Employee name"+cs.getString(4));
				System.out.println("Employee name"+cs.getString(1));
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
