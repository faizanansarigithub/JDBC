package com.fa.jdbc;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class PsBLOBRetriveUsingOracle {
	private static final String SELECT_QUERY="select * from artist_info where artistid=?";

	public static void main(String[] args) {
		Scanner sc=null;
		int sno=0,artistId=0;
		String name=null,addrs=null;
		float income=0.0f;
		InputStream photoIS=null,videoIS=null;
		OutputStream photoOS=null,videoOS=null;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter sno");
				sno=sc.nextInt();
			}//if
			//established the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager");
			//create preparedStatement obj
			if(con!=null)
				ps=con.prepareStatement(SELECT_QUERY);
			//set query param
			if(ps!=null) 
				ps.setInt(1, sno);
			//execute query
			if(ps!=null)
				rs=ps.executeQuery();
			if(rs!=null) {
				if(rs.next()) {
					sno=rs.getInt(1);
					name=rs.getString(2);
					addrs=rs.getString(3);
					income=rs.getFloat(4);
					photoIS=rs.getBinaryStream(5);
					videoIS=rs.getBinaryStream(6);
					System.out.println(sno+"   "+name+"   "+addrs+"   "+income);
					//convert inputStream to outputStream
					photoOS=new FileOutputStream("new_photo.jpg");
					videoOS=new FileOutputStream("new_video1.mp4");
				}
				if(photoIS !=null && videoIS!=null) {
					IOUtils.copy(videoIS, photoOS);
					IOUtils.copy(videoIS, videoOS);
				}
				System.out.println("BLOB values are retrive");
			}
			else {
				System.out.println("Record not found");
			}
		}//try
		
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

			try {
				if (photoIS != null)
					photoIS.close();
			} catch (Exception se) {
				se.printStackTrace();
			}
			try {
				if (videoIS != null)
					videoIS.close();
			} catch (Exception se) {
				se.printStackTrace();
			}
		} // finally
	}

}
