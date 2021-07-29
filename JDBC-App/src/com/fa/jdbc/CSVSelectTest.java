package com.fa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CSVSelectTest {
	private static final String CSV_SELECT="SELECT * FROM file1.csv";

	public static void main(String[] args) {
	try(Connection con=DriverManager.getConnection("jdbc:Text:///F:/workspace/JDBCCLass")) {
		try(PreparedStatement ps=con.prepareStatement(CSV_SELECT)) {
			try(ResultSet rs=ps.executeQuery()) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"   "+rs.getFloat(4));
				}
			}
			
		}
	}//try1
	catch(SQLException se) {
		se.printStackTrace();
	}
	catch(Exception e) {
		e.printStackTrace();
	}
	}

}
