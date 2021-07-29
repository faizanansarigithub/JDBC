package com.fa.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import oracle.jdbc.OracleTypes;

/*CREATE OR REPLACE FUNCTION FX_VIEW_DELETE_STUD_BY_NO 
(
  NO IN NUMBER 
, STUDENTDETAILS OUT SYS_REFCURSOR 
) RETURN VARCHAR2 AS 
    RESULT VARCHAR2 (30);
    CNT NUMBER(3);
BEGIN
    OPEN STUDENTDETAILS FOR
    SELECT SNO,SNAME,SADD,AVG FROM STUDENT WHERE SNO=NO;
    
    DELETE FROM STUDENT WHERE SNO=NO;
    
    CNT:=SQL%ROWCOUNT;
    IF(CNT=1) THEN
      RESULT:='RECORD DELETED';
    ELSE
        RESULT:='RECORD NOT FOUND FOR DELETION';
        END IF;
    
  RETURN RESULT;
END FX_VIEW_DELETE_STUD_BY_NO;*/
public class CsSelectAndNonSelectTst {
	private static final String CALL_FUNCTOIN_QUERY="{?=call FX_VIEW_DELETE_STUD_BY_NO(?,?)}";
	
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		CallableStatement cs=null;
		int no=0;
		ResultSet rs=null;
		try {
		sc=new Scanner(System.in);
		if(sc!=null) {
		System.out.println("Enter Student number ");
		no=sc.nextInt();
			}
		//established the connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager");
		//create CallableStatement object
		if(con!=null)
			cs=con.prepareCall(CALL_FUNCTOIN_QUERY);
		//Register OUT param WIth JDBC type
		if(cs!=null) {
			cs.registerOutParameter(1, Types.VARCHAR);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
		}
		//set IN param
		if(cs!=null)
			cs.setInt(2, no);
		//execute PL/SQL funtion
		if(cs!=null)
			cs.execute();
		//process the result
		rs=(ResultSet) cs.getObject(3);
		//gathr the result
		if(rs!=null) {
			if(rs.next()) {
				System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"    "+rs.getString(3)+"   "+rs.getFloat(4));
			}
			else
				System.out.println("Record not Found");
		}
		System.out.println("Result:    "+cs.getString(1));
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
