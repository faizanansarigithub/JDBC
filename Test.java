/Write a jdbc app to get student details whose result is fail
// Select * from student2 where result == fail

/** ..............*/indentation

import java.sql.*; // for jdbc API
import java.util.Scanner; // for Scanner class

class StudentNew
{
public static void main(String[] args)throws Exception
{

Scanner sc=new Scanner(System.in); //for reading inputs

System.out.println("Show list of Students who is  (passed : failed)");
         String result=sc.nextLine();

//load and register jdbc driver class s/w
         Class.forName("oracle.jdbc.driver.OracleDriver");

//establish connection with jdbc software
Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","myoracle");

//create statement object
Statement st=con.createStatement();

        //prepare SQL query
       String query="select * from student2 where result="+"'"+result"'";
        //System.out.println(query);


	//send and execute SQL Query in DB s/w
	ResultSet rs=st.executeQuery(query);

    boolean flag=false;

	while(rs.next())
	{
	flag=true;
		System.out.println(rs.getString("sname")+"  "+rs.getString("sage")+"     "
		+rs.getString("class")+"        "+rs.getString("result"));
	}

	if(flag==false)
	System.out.println("NO RECORDS FOUND!");

	}
}