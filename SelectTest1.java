//SelectTest1.java
import java.sql.*;//for jdbc api
import java.util.*;//for Scanner
//Write a JDBC App to get student details based on given start, end range of student number

//select * from student where sno>=10 and sno<=50;
class SelectTest1 
{
	public static void main(String[] args)throws Exception
	{
		//read inputs
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter start range of student number::");
		int start=sc.nextInt();//10
		System.out.println("Enter end range of student number::");
		int end=sc.nextInt();//50
		//register jdbc drivers /w
		Class.forName("oracle.jdbc.driver.OracleDriver");//optional

		//establish the connection
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","manager");
		System.out.println(con.getClass().getName());
		//create Statement object
		Statement st=con.createStatement();
		//prepare SQL query
		    //select * from student where sno>=10 and sno<=50;
		String query="select * from student where sno>="+start+"and sno<="+end;
		System.out.println(query);

		//send and execute SQL Query in DB s/w
		ResultSet rs=st.executeQuery(query);
		boolean flag=false;
		while(rs.next()){
			flag=true;
			/*System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));*/
			System.out.println(rs.getString("sno")+"   "+rs.getString("sname")+"  "+rs.getString("sadd")+"  "+rs.getString("avg"));

		}
		if(flag==false)
			System.out.println("NO record found");
		
		//close jdbc objects
		rs.close();
		st.close();
		con.close();
		sc.close();


		
	}
}
