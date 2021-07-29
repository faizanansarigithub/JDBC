package com.fa.jdbc;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ScrollGUIApplication extends JFrame implements ActionListener {
	private static final String GET_STUDENT_QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";
	private JLabel lno,lname,ladd,lavg,lmsg;
	private JTextField tno,tname,tadd,tavg;
	private JButton bFirst,bNext,bPrevious,bLast;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	
	
	public ScrollGUIApplication() {
		setTitle("GUI Application");
		setLayout(new FlowLayout());
		setSize(200,400);
		//add comp
		lno=new JLabel("Student Number");
		add(lno);
		tno=new JTextField(10);
		add(tno);
		lname=new JLabel("Student Name");
		add(lname);
		tname=new JTextField(10);
		add(tname);
		ladd=new JLabel("Student Address");
		add(ladd);
		tadd=new JTextField(10);
		add(tadd);
		lavg=new JLabel("Student Average");
		add(lavg);
		tavg=new JTextField(10);
		add(tavg);
		//add button
		bFirst=new JButton("First");
		add(bFirst);
		
		bNext=new JButton("Next");
		add(bNext);
		
		bPrevious=new JButton("Previous");
		add(bPrevious);
		
		bLast=new JButton("Last");
		add(bLast);
		
		bFirst.addActionListener(this);
		bNext.addActionListener(this);
		bPrevious.addActionListener(this);
		bLast.addActionListener(this);
		
		lmsg=new JLabel("");
		add(lmsg);
		
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 jdbcInitialized();
	}
	private void jdbcInitialized() {
		try {
			//Establish the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system", "manager");
			//create preparedStaatment object
			if(con!=null)
			ps=con.prepareStatement(GET_STUDENT_QUERY ,
																				ResultSet.TYPE_SCROLL_SENSITIVE,
																				ResultSet.CONCUR_UPDATABLE);
			//createScrollable Resultset object
			if(ps!=null)
				rs=ps.executeQuery();
			
		}//
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		//System.out.println("main method call");
	new ScrollGUIApplication();
	

	}


	@Override
	public void actionPerformed(ActionEvent ae) {
		boolean flag =false;
		//System.out.println("Action perform");
		if(ae.getSource()==bFirst) {
			//System.out.println("First Button");
			
			try {
				rs.first();
				flag=true;
				
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		else if(ae.getSource()==bNext) {
			//System.out.println("Next Button");
			lmsg.setText("");
			try {
				if(!rs.isLast()) {
				rs.next();
			flag=true;
				
			}
				else {
					lmsg.setText("Do not click  U R already in last Record");
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
			
		
		else if(ae.getSource()==bPrevious) {
			//System.out.println("Previous  Button");
				lmsg.setText("");
			
			try {
				if(!rs.isFirst()) {
				rs.previous();
				flag=true;
				
			}
				else {
					lmsg.setText("Do not click  U R already in First Record");
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		else {
			//System.out.println("Last");
			lmsg.setText("");
			try {
				rs.last();
				
				flag=true;
				
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
		if(flag==true) {
			try {
				tno.setText(rs.getString(1));
				tname.setText(rs.getString(2));
				tadd.setText(rs.getString(3));
				tavg.setText(rs.getString(4));
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}

	
}
