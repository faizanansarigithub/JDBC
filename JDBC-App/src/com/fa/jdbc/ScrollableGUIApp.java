package com.fa.jdbc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class ScrollableGUIApp extends JFrame {
	private static final String GET_STUDENT_QUERY="SELECT SNO,SNAME,SADD,AVG FROM STUDENT";

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ScrollableGUIApp frame = new ScrollableGUIApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
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
	public ScrollableGUIApp() {
		boolean falg=false;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1049, 665);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Number");
		lblNewLabel.setBounds(110, 95, 218, 43);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Student Name");
		lblNewLabel_1.setBounds(110, 189, 147, 32);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Student Address");
		lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(110, 286, 130, 32);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Student Average");
		lblNewLabel_3.setForeground(new Color(255, 160, 122));
		lblNewLabel_3.setBounds(110, 378, 147, 32);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(362, 105, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(362, 194, 116, 22);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setForeground(new Color(128, 0, 0));
		textField_2.setBounds(362, 291, 116, 22);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setForeground(new Color(148, 0, 211));
		textField_3.setBounds(362, 361, 116, 22);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("first");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					rs.first();
					textField.setText(rs.getString(1));
					textField_1.setText(rs.getString(2));
					textField_2.setText(rs.getString(3));
					textField_3.setText(rs.getString(4));
					
				}
				catch(SQLException se) {
					se.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBounds(175, 500, 147, 63);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("next");
		btnNewButton_1.setForeground(new Color(255, 0, 255));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ScrollGUIApplication a=new ScrollGUIApplication();
			//	a.setVisible(true);
				//dispose(); 
				
			}
		});
		btnNewButton_1.setBounds(360, 500, 118, 63);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("previous");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(543, 500, 97, 25);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("last");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnNewButton_3.setBounds(748, 500, 97, 25);
		contentPane.add(btnNewButton_3);
		
		JLabel lblNewLabel_4 = new JLabel("Student Information");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel_4.setBounds(431, 26, 338, 32);
		contentPane.add(lblNewLabel_4);
		jdbcInitialized() ;
	}
	 
}
