package com.fa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrollableRSTest3 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int i=0;
		
		try {
			//established the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "manager");
			//create Statment object
			//st=con.createStatement();
			st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			//st=con.createStatement(1005,1008);
			//Create Resultset object
			if(st!=null)
				rs=st.executeQuery("SELECT SNO,SNAME,SADD,AVG FROM STUDENT");
			//process the resultset
		
			if(rs!=null) {
				/*
				 * while(rs.next()) { //rs.refreshRow(); if(i==0) Thread.sleep(30000);
				 * System.out.println(rs.getRow()+"---->"+rs.getInt(1)+"   "+rs.getString(2)
				 * +"  "+rs.getString(3)+"      "+rs.getFloat(4)); i++; }//while
				 */		
				/*
				 * System.out.println("..............insert operatoin.........................."
				 * ); //insert rs.moveToInsertRow(); rs.updateInt(1, 9000); rs.updateString(2,
				 * "janedar"); rs.updateString(3, "hyd"); rs.updateFloat(4, 20.89f);
				 * rs.insertRow();
				 */
				/*
				 * System.out.println("..................update operation....................");
				 * rs.absolute(4); rs.updateString(2, "BigBoss"); rs.updateRow();
				 */
				System.out.println("...............delete operation...................");
				rs.absolute(4);
				rs.deleteRow();
				
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
				if(st!=null)
					st.close();
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
			
			
		}//finally
	}//main
}//class
