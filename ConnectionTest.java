import java.sql.*;
class ConnectionTest
{
	public static void main(String[] args)throws Exception{
	//register oracle thin driver with DriverManager service
	//create jdbc driver class object
	/*
	oracle.jdbc.driver.OracleDriver driver=new oracle.jdbc.driver.OracleDriver();
	//register jdbc driver
	DriverManager.registerDriver(driver);*/


	//Class.forName("oracle.jdbc.driver.OracleDriver");
	//oracle.jdbc.driver.OracleDriver dddd=new oralce.jdbc.driver.OracleDriver();
	//new oralce.jdbc.driver.OracleDriver();
	//establish the connection with DB s/w
	//DriverManager.getConnection("URL","USERNAME","PASSWORD");
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager");
	//check the connection
	if(con==null)
		System.out.println("Connecction is not established");
	else
		System.out.println("Connecction is established");
	}
}
//Windows XP USB
//ANDROID 
//WINDOWS VIsta

