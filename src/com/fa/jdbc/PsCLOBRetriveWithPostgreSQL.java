package com.fa.jdbc;

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

public class PsCLOBRetriveWithPostgreSQL {
	private static final String SELECT_QUERY_NAUKRI="SELECT SNO,NAME,ADDRS,RESUME FROM NAUKRI_INFO WHERE SNO=?";

	public static void main(String[] args) {
		Scanner sc= null;
		String name=null,addrs=null,resumePath=null;
		Connection con=null;
		PreparedStatement ps=null;
		Reader reader=null;
		int result=0,sno=0;
		ResultSet rs=null;
		Writer writer=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter your SNO");
				sno=sc.nextInt();
			}//if
			//establish the connection
			con=DriverManager.getConnection("jdbc:postgresql:jdbc01","postgres","root");
			
			//create preparedStatement object
			if(con!=null)
				ps=con.prepareStatement(SELECT_QUERY_NAUKRI);
			//set value in pram
			if(ps!=null)
				ps.setInt(1, sno);
			//execute the query
			if(ps!=null)
				rs=ps.executeQuery();
			//process the result
			if(rs!=null) {
				if(rs.next()) {
				result=rs.getInt(1);
				name=rs.getString(2);
				addrs=rs.getString(3);
				reader=rs.getCharacterStream(4);
				writer=new FileWriter("new_file.txt");
				if(reader!=null && writer!=null) {
					IOUtils.copy(reader, writer);
				}
				System.out.println(result+ "    "+name+"    "+addrs);
				System.out.println("Record found");
				}
				else {
					System.out.println("Record not found");
				}
			}
			
		}//try
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (con != null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

			try {
				if (sc != null)
					sc.close();
			} catch (Exception se) {
				se.printStackTrace();
			}

			
		} // finally
	}//main
}//class
