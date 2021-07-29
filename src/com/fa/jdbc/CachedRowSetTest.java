package com.fa.jdbc;

import java.sql.SQLException;

import oracle.jdbc.rowset.OracleCachedRowSet;

public class CachedRowSetTest {

	public static void main(String[] args) {
		try(OracleCachedRowSet crowset = new  OracleCachedRowSet()){
			//set jdbc properties
			crowset.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL");
			crowset.setUsername("system");
			crowset.setPassword("manager");
			
			//set SQL Query
			crowset.setCommand("SELECT SNO,SNAME,SADD,AVG FROM STUDENT");
			//Excute the sql query 
			crowset.execute();
			//process the result
			while(crowset.next()) {
					System.out.println(crowset.getInt(1)+"    "+crowset.getString(2)+"    "+crowset.getString(3)+"    "+crowset.getString(4));
			}
			System.out.println("...................................Stop DB s/w...............................");
			Thread.sleep(40000);
			crowset.absolute(4);
			crowset.updateString(3,"Landon");
			crowset.updateRow();
			System.out.println("...........................................Start DB s/w...............................");
			Thread.sleep(80000);
			//process the RowSet
			crowset.acceptChanges();
			while(crowset.next()) {
					System.out.println(crowset.getInt(1)+"    "+crowset.getString(2)+"    "+crowset.getString(3)+"    "+crowset.getString(4));
				}
			}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
