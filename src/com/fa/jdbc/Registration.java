package com.fa.jdbc;

import java.util.Scanner;

public class Registration {
	private String name=null,addrs=null,email=null,gender=null,dob=null,resume=null,photo=null,txt=null;
	private long mobile=0L;
	private float age=0.0f;
	Scanner sc=null;
	public void registration() {
		sc=new Scanner(System.in);
		if(sc!=null) {
			System.out.println("Enter Your Name");
			name=sc.next();
			System.out.println("Enter Your Gender Male or Female");
			gender=sc.next();
			System.out.println("Enter Your DOB dd-MM-yyyy");
			dob=sc.next();
			System.out.println("Enter Your Addrs");
			addrs=sc.next();
			System.out.println("Enter Your E-mail ID");
			email=sc.next();
			System.out.println("Enter Your Mobile Number");
			mobile=sc.nextLong();
			System.out.println("Enter Your Age");
			age=sc.nextFloat();
			System.out.println("Select your Reume Location path ");
			resume=sc.next();
			System.out.println("Select your photo Location path ");
			photo=sc.next();
			System.out.println("Select your Text file Location path ");
			txt=sc.next();
			
		}
	}

}
