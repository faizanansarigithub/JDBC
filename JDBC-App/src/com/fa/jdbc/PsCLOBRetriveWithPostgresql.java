package com.fa.jdbc;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class PsCLOBRetriveWithPostgresql {
	private static final String SELECT_QUERY_CLOB="SELECT REGNO,NAME,ADDRS,QLY,RESUME FROM NAUKRI_INFO WHERE REGNO=?";
	public static void main(String[] args) {
		Scanner sc=null;
		int regno=0;
		String name=null,addrs=null,qly=null,resumeLocation=null;
		Connection con=null;
		PreparedStatement ps=null;
		Reader reader=null;
		int result=0;
		ResultSet rs=null;
		Writer writer=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter your REGISTRATOIN NO");
				regno=sc.nextInt();
				
			}//if
			//register jdbc driver s/w
			//Class.forName("org.postgresql.Driver");
			//established the connection
			con=DriverManager.getConnection("jdbc:postgresql:jdbc_classes","postgres","root");
			if(con!=null)
				ps=con.prepareStatement(SELECT_QUERY_CLOB);
			//set values in param
			if(ps!=null) 
				ps.setInt(1, regno);
			//execute query
			if(ps!=null)
			rs=ps.executeQuery();
			if(rs!=null) {
				if(rs.next()) {
					result=rs.getInt(1);
					name=rs.getString(2);
					addrs=rs.getString(3);
					qly=rs.getString(4);
					reader=rs.getCharacterStream(5);
					writer=new FileWriter("newResume.txt");
					if(reader!=null && writer!=null) {
						IOUtils.copy(reader, writer);
					}//if
					System.out.println("Record found");
					System.out.println(result+"  "+name+"  "+addrs+"   "+qly);
				}
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
		finally {
			//close jdbc objects
			try {
				if(ps!=null)
					ps.close();
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
			catch(Exception se) {
				se.printStackTrace();
			}
			
			try {
				if(reader!=null)
					reader.close();
			}
			catch(Exception se) {
				se.printStackTrace();
			}
			
			try {
				if(writer!=null)
					writer.close();
			}
			catch(Exception se) {
				se.printStackTrace();
			}
		}//finally
	}//main
}//class
