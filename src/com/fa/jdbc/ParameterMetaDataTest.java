package com.fa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ParameterMetaDataTest {
	private static final String GET_ALL_STUDENT_QUERY="INSERT INTO STUDENT VALUES(?,?,?,?)";

	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager")) {
			if(con!=null)
				try(PreparedStatement ps=con.prepareStatement(GET_ALL_STUDENT_QUERY)) {
					//create ParameterMetaData object
					ParameterMetaData pmd=null;
					if(ps!=null)
						pmd=ps.getParameterMetaData();
					//get more info about parameters
					int count =pmd.getParameterCount();
					for(int i=1;i<=count;i++) {
						System.out.println("parameter number"+i);
						System.out.println("parameter mode"+pmd.getParameterMode(i));
						System.out.println("parameter type name"+pmd.getScale(i));
					}
				}
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}
	}

}
