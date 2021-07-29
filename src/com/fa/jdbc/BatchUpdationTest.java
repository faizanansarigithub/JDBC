package com.fa.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchUpdationTest {

	public static void main(String[] args) {
		try(Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager")) {
			//create Statement object
			if(con!=null)
				try(Statement st=con.createStatement()) {
					//add query to the batch
					st.addBatch("insert into student values(1004,'karan','hyd',90.09)");
					st.addBatch("insert into student values(1005,'jisan','hyd',34.09)");
					st.addBatch("update student set avg=avg+5 where sno>=1000");
					st.addBatch("delete from student where sno<=100");
					//execute the batch
					int result[]=st.executeBatch();
					//process the int[]
					int total=0;
					for (int i = 0; i < result.length; i++) {
						total=total+result[i];
					}
					System.out.println("no. of records that are effected::"+total);
				}//try2
		}//try1
		catch(SQLException se) {
			se.printStackTrace();
		}
	}

}
