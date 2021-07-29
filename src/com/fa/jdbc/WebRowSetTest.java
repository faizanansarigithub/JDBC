package com.fa.jdbc;

import java.io.FileWriter;
import java.io.Writer;
import java.sql.SQLException;

import oracle.jdbc.rowset.OracleWebRowSet;

public class WebRowSetTest {

	public static void main(String[] args) {
		try(OracleWebRowSet wrowset = new  OracleWebRowSet()){
			//set jdbc properties
			wrowset.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL");
			wrowset.setUsername("system");
			wrowset.setPassword("manager");
			
			//set SQL Query
			wrowset.setCommand("SELECT SNO,SNAME,SADD,AVG FROM STUDENT");
			//Excute the sql query 
			wrowset.execute();
			//process the result
			while(wrowset.next()) {
					System.out.println(wrowset.getInt(1)+"    "+wrowset.getString(2)+"    "+wrowset.getString(3)+"    "+wrowset.getString(4));
			}
			System.out.println("Write RowSet object data to xml file");
			Writer writer=new FileWriter("student_info.xml");
			wrowset.writeXml(writer);
			System.out.println("Write RowSet object data on the console in xml formate");
			wrowset.writeXml(System.out);
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
