package com.fa.jdbc;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class SelectTestWithPropertiesFile {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		boolean flag=false;
		try {
		
			InputStream is=new FileInputStream("src/com/fa/common/jdbc.properties");
			Properties props=new Properties();
			props.load(is);
			System.out.println(props);
			//Resister oracle driver
				Class.forName(props.getProperty("jdbc.driver"));
			//establish the connectoin
			con=DriverManager.getConnection(props.getProperty("jdbc.url"),
																					props.getProperty("jdbc.user"),
																					props.getProperty("jdbc.password"));
			//create statement objects
			if(con!=null) 
				st=con.createStatement();
			if(st!=null)
				rs=st.executeQuery("SELECT * FROM STUDENT");
			//process the ResultSet
			if(rs!=null) {
				while(rs.next()) {
					flag=true;
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
				}
				if(flag)
					System.out.println("REcord  Found");
				else
					System.out.println("Record not found");
			}
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
