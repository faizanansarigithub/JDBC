package com.fa.jdbc;

import java.io.FileWriter;
import java.io.Writer;
import java.sql.SQLException;

import oracle.jdbc.rowset.OracleCachedRowSet;
import oracle.jdbc.rowset.OracleJoinRowSet;

public class JoinRowSetTest {

	public static void main(String[] args) {
		try(OracleCachedRowSet crowset1 = new  OracleCachedRowSet();
				OracleCachedRowSet crowset2 = new  OracleCachedRowSet();
				OracleJoinRowSet jrowset = new  OracleJoinRowSet();){
			//set jdbc properties
			crowset1.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL");
			crowset1.setUsername("system");
			crowset1.setPassword("manager");
			
			//set SQL Query
			crowset1.setCommand("SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP");
			crowset1.setMatchColumn(5);
			crowset1.execute();
			
			crowset2.setUrl("jdbc:oracle:thin:@localhost:1521:ORCL");
			crowset2.setUsername("system");
			crowset2.setPassword("manager");
			
			//set SQL Query
			crowset2.setCommand("SELECT DEPTNO ,DNAME,LOC FROM DEPT");
			crowset2.setMatchColumn(1);
			//Excute the sql query 
			crowset2.execute();
			//add multiple cached rowsets to joinrowsets
			jrowset.addRowSet(crowset2);
			jrowset.addRowSet(crowset1);
			//process the result
			while(jrowset.next()) {
					System.out.println(jrowset.getString(1)+"    "+jrowset.getString(2)+"    "+jrowset.getString(3)+"    "+jrowset.getString(4)
					+jrowset.getString(5)+"    "+jrowset.getString(6)+"    "+jrowset.getString(7));
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
