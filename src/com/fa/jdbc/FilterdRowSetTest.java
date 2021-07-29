package com.fa.jdbc;

import java.sql.SQLException;

import javax.sql.RowSet;
import javax.sql.rowset.Predicate;

import oracle.jdbc.rowset.OracleFilteredRowSet;

public class FilterdRowSetTest {

	public static void main(String[] args) {
		try(OracleFilteredRowSet frowset = new  OracleFilteredRowSet()){
			//set jdbc properties
			frowset.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL");
			frowset.setUsername("system");
			frowset.setPassword("manager");
			
			//set SQL Query
			frowset.setCommand("SELECT EMPNO,ENAME,JOB,DEPTNO FROM EMP");
			frowset.setFilter(new Filter1("ENAME", "A"));
			//Excute the sql query 
			frowset.execute();
			//process the result
			while(frowset.next()) {
					System.out.println(frowset.getInt(1)+"    "+frowset.getString(2)+"    "+frowset.getString(3)+"    "+frowset.getString(4));
			}
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}//main
	private static class Filter1 implements Predicate{
		private String colName;
		private String condData;
		public Filter1(String colName,String condData) {
			this.colName=colName;
			this.condData=condData;
		}

		@Override
		public boolean evaluate(RowSet rs) {
			try {
				String colValue=rs.getString(colName);
				if(colValue.startsWith(condData))
					return true;
				else
					return false;
			}
			catch(SQLException se) {
				se.printStackTrace();
				return false;
			}
		}

		@Override
		public boolean evaluate(Object value, int column) throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean evaluate(Object value, String columnName) throws SQLException {
			// TODO Auto-generated method stub
			return false;
		}
		
	}

}
