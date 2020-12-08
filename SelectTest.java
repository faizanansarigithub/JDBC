import java.sql.*;
class SelectTest 
{
	public static void main(String[] args)throws Exception 
	{
	//establish the connection with DB s/w
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager");
	//create Statemet object
	Statement st = con.createStatement();
	//send and execute SQL Query in DB s/w
	ResultSet rs=st.executeQuery("SELECT * FROM STUDENT");
	//process the ResultSet object

	while(rs.next() !=false){
		System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
	}
	//close jdbc objects
	rs.close();
	st.close();
	con.close();
	}
}
