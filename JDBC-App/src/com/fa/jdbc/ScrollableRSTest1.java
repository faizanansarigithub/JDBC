package com.fa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ScrollableRSTest1 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		
		try {
			//established the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "system", "manager");
			//create Statment object
			//st=con.createStatement();
			//st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			st=con.createStatement(1005,1008);
			//Create Resultset object
			if(st!=null)
				rs=st.executeQuery("SELECT SNO,SNAME,SADD,AVG FROM STUDENT");
			//process the resultset
			if(rs!=null) {
				System.out.println("Record from top to bottom");
				while(rs.next()) {
					System.out.println(rs.getRow()+"---->"+rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3)+"      "+rs.getFloat(4));
				}//while
				
				System.out.println("Bottom to Top");
				while(rs.previous()) {
					System.out.println(rs.getRow()+"---->"+rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3)+"      "+rs.getFloat(4));
				}//while
				System.out.println("Random records are display");
				rs.first();
				System.out.println(rs.getRow()+"---->"+rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3)+"      "+rs.getFloat(4));
				rs.last();
				System.out.println(rs.getRow()+"---->"+rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3)+"      "+rs.getFloat(4));
				rs.relative(-2);
				System.out.println(rs.getRow()+"---->"+rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3)+"      "+rs.getFloat(4));
				rs.absolute(4);
				System.out.println(rs.getRow()+"---->"+rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3)+"      "+rs.getFloat(4));
				rs.absolute(-3);
				System.out.println(rs.getRow()+"---->"+rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3)+"      "+rs.getFloat(4));
				rs.absolute(2);
				System.out.println(rs.getRow()+"---->"+rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3)+"      "+rs.getFloat(4));
				
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
