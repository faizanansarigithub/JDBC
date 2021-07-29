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

public class PsBLOBRetriveUsingMySQL {
	private static final String SELECT_QUERY="select * from shadi_dot_com where dulhano=?";

	public static void main(String[] args) {
		Scanner sc=null;
		int sno=0,id=0;
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String name=null,addrs=null;
		InputStream photoIS=null,videoIS=null;
		float income=0.0f;
		OutputStream photoOS=null,videoOS=null;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Seriol Number");
				sno=sc.nextInt();
			}//if
			//establish the connectoin
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","root");
			if(con!=null)
				ps=con.prepareStatement(SELECT_QUERY);
			//set param
			if(ps!=null) 
				ps.setInt(1,sno);
			//execute the query
			if(ps!=null)
				rs=ps.executeQuery();
			//process the resultset
			if(rs!=null) {
				if(rs.next()) {
					id=rs.getInt(1);
					name=rs.getString(2);
					addrs=rs.getString(3);
					income=rs.getFloat(4);
					photoIS=rs.getBinaryStream(5);
					videoIS=rs.getBinaryStream(6);
					System.out.println(id+"   "+name+"  "+addrs+"  "+income);
					photoOS=new FileOutputStream("new_pic.jgp");
					videoOS=new FileOutputStream("new_video.mp4");
					//copy inputStreame to outputStreame
					if(photoIS!=null && videoIS!=null) {
						IOUtils.copy(photoIS, photoOS);
						IOUtils.copy(videoIS, videoOS);
					}
					System.out.println("BLOB values are retrive");
				}
				else {
					System.out.println("Record not found");
				}
			}
			
		}
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
	}//main
}//class
