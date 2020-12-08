//SelectTest2.java
package com.fa.jdbc;//create package
/** JDBC App to get Emp details based on given 3 desgs
 * version :: 1.0
 * author :: Teat-FA*/
import java.util.Scanner;//explicit pack import
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class SelectTest02 
{
	public static void main(String[] args) 
	{
		Scanner sc=null;
		String desg1=null,desg2=null,desg3=null;
		String cond=null;
		Statement st=null;
		Connection con=null;
		String query = null;
		ResultSet rs=null;
		boolean flag=false;
		try{
			//read input
			sc=new Scanner(System.in);
			if(sc!=null){
				System.out.println("Enter desgs1");
				desg1=sc.next().toUpperCase();
				System.out.println("Enter desgs2");
				desg2=sc.next().toUpperCase();
				System.out.println("Enter desgs3");
				desg3=sc.next().toUpperCase();
			}//if
			//conver input values as the required for SQL query ('CLERK','SALESMAN','MANAGER')
			cond="('"+desg1+"','"+desg2+"','"+desg3+"')";
			System.out.println(cond);

			//register JDBC driver s/w
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager");
			//crate Statement object
			if(con!=null)//to avoid NPE
			st=con.createStatement();
			//prepare SQL Query
	// select empno,ename,job,sal from emp where job in('CLERK','SALESMAN','manager') order by job;
			query= "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN"+cond+"ORDER BY JOB";
			System.out.println(query);
			//send and execute SQL Query in DB s/w
			if(st!=null)
				rs=st.executeQuery(query);
			//process the ResltSet object
			if(rs!=null){
				while(rs.next()){
					flag=true;
					System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4));
				}//while
				if(flag)
					System.out.println("Record display");
				else
					System.out.println("No record found");
			}//if

		}//try
		catch(SQLException se){//to handle known exception
			se.printStackTrace();
		}
		catch(Exception e){//to handle Unknown exception
			e.printStackTrace();
		}
		finally{
			//close jdbc objs
			/*
			try{
				if(rs!=null && st!=null && con != null){
					rs.close();
					st.close();
					con.close();
					sc.close();
				}//if
			}//try
			catch(SQLException se){
				se.printStackTrace();
			}//
			*/

			try{
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}

			try{
				if(st!=null)
					st.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}

			try{
				if(con!=null)
					con.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}

			try{
				if(sc!=null)
					sc.close();
			}
			catch(Exception se){
				se.printStackTrace();
			}


		}//finally
	}//main
}//class


//cmd> javac -d . SelectTest2.java
//cmd java com.fa.jdbc.SelectTest2