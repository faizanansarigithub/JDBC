package com.fa.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.pool.OracleConnectionPoolDataSource;

public class PooledConnectionTest {

	public static void main(String[] args) {
		OracleConnectionPoolDataSource ds=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			ds=new OracleConnectionPoolDataSource();
			ds.setURL("jdbc:oracle:thin:@localhost:1521:ORCL");
			ds.setUser("system");
			ds.setPassword("manager");//use all these details and creates jdbc con pool having initial 4 jdbc con object(default)
			//get pooled jdbc connection obj
			con=ds.getConnection();
			//create Statement obj
			st=con.createStatement();
			//send and execute SQL Query
			rs=st.executeQuery("SELECT SNO,SNAME,SADD,AVG FROM STUDENT");
			//PROCESS the result set
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"    "+rs.getString(2)+"    "+rs.getString(3)+"    "+rs.getString(4));
				
			}
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
	}

}
