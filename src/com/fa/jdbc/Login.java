package com.fa.jdbc;

import java.util.Scanner;

public class Login {
	private static String name=null,mobile=null;
	private static Scanner sc=null;
	public static void login() {
	System.out.println("Enter Your Name");
	name=sc.next();
	System.out.println("Enter Your Mobile number");
	mobile=sc.next();
	}

}
