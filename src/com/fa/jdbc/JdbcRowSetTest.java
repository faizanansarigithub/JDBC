package com.fa.jdbc;

import java.sql.SQLException;

import oracle.jdbc.rowset.OracleJDBCRowSet;
import oracle.net.aso.j;

public class JdbcRowSetTest {

	public static void main(String[] args) {
		try(OracleJDBCRowSet jrowset=new OracleJDBCRowSet()){
			//set jdbc properties
			jrowset.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL");
			jrowset.setUsername("system");
			jrowset.setPassword("manager");
			
			//set SQL Query
			jrowset.setCommand("SELECT SNO,SNAME,SADD,AVG FROM STUDENT");
			//Excute the sql query 
			jrowset.execute();
			//process the result
			int count=0;
			while(jrowset.next()) {
				
				if(count==0)
					Thread.sleep(40000);
					jrowset.refreshRow();
					System.out.println(jrowset.getInt(1)+"    "+jrowset.getString(2)+"    "+jrowset.getString(3)+"    "+jrowset.getString(4));
					count++;
			}
			jrowset.absolute(4);
			System.out.println(jrowset.getInt(1)+"    "+jrowset.getString(2)+"    "+jrowset.getString(3)+"    "+jrowset.getString(4));
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
