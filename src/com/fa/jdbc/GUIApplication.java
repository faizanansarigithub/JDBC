package com.fa.jdbc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUIApplication extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIApplication frame = new GUIApplication();
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
	public GUIApplication() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1034, 755);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Number");
		lblNewLabel.setBounds(140, 124, 162, 33);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Student Name");
		lblNewLabel_1.setBounds(140, 214, 162, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Student Address");
		lblNewLabel_2.setBounds(140, 316, 162, 42);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Student Average");
		lblNewLabel_3.setBounds(140, 439, 162, 42);
		contentPane.add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(489, 124, 254, 33);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(489, 214, 254, 33);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(489, 321, 254, 33);
		contentPane.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(489, 427, 254, 33);
		contentPane.add(textField_3);
		
		JButton btnNewButton = new JButton("First");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnNewButton.setFont(new Font("Wide Latin", Font.BOLD, 30));
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setBounds(140, 537, 206, 58);
		contentPane.add(btnNewButton);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		btnNext.setForeground(Color.ORANGE);
		btnNext.setFont(new Font("Wide Latin", Font.BOLD, 30));
		btnNext.setBackground(Color.RED);
		btnNext.setBounds(385, 537, 206, 58);
		contentPane.add(btnNext);
		
		JButton btnPrevious = new JButton("Prev");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnPrevious.setForeground(Color.ORANGE);
		btnPrevious.setFont(new Font("Wide Latin", Font.BOLD, 30));
		btnPrevious.setBackground(Color.RED);
		btnPrevious.setBounds(634, 537, 199, 58);
		contentPane.add(btnPrevious);
		
		JButton btnLast = new JButton("Last");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		btnLast.setForeground(Color.ORANGE);
		btnLast.setFont(new Font("Wide Latin", Font.BOLD, 30));
		btnLast.setBackground(Color.RED);
		btnLast.setBounds(385, 637, 206, 58);
		contentPane.add(btnLast);
		
		JLabel lresult = new JLabel("");
		lresult.setForeground(Color.GREEN);
		lresult.setBackground(Color.RED);
		lresult.setFont(new Font("Tahoma", Font.BOLD, 30));
		lresult.setBounds(334, 13, 340, 53);
		contentPane.add(lresult);
	}
}
