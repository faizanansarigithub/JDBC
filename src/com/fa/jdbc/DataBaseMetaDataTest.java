package com.fa.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseMetaDataTest {

	public static void main(String[] args) {
		Connection con=null;
		try {
		//Resister oracle driver
		//	Class.forName("oracle.jdbc.driver.OracleDriver");
		//establish the connectoin
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager");
			//create MetaData object
			DatabaseMetaData dbmd=null;
			if(con!=null)
				dbmd=con.getMetaData();
			//use DatabaseMetadata obj to know more about underlying DB s/w
			if(dbmd!=null) {
				System.out.println("DataBase Name ::"+dbmd.getDatabaseProductName());
				System.out.println("DataBase Version ::"+dbmd.getDatabaseMajorVersion()+" and "+dbmd.getDatabaseMinorVersion());
				System.out.println("DataBase Complete Name with version ::"+dbmd.getDatabaseProductVersion());
				System.out.println("Maximum Row size ::"+dbmd.getMaxRowSize());
				System.out.println("Maximum column in select SQL Query::"+dbmd.getMaxColumnsInSelect());
				System.out.println("Maximum column in DB table ::"+dbmd.getMaxColumnsInTable());
			}//if
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
