package com.fa.basic;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class DateConversion {

	public static void main(String[] args) throws Exception {
		System.out.println("\\");
		//  op / \/
	//converting String values to java.util.Date class obj
		String d1="12-11-1990"; //dd-MM-yyyy
		SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date ud1=sdf.parse(d1);
		System.out.println("util date   "+ud1);
		
		//convertint java.util.Date object to java.sql.Date class obj
		long ms=ud1.getTime();
		java.sql.Date sd1=new java.sql.Date(ms);
		System.out.println("SQL date   "+sd1);
		
		String d2="1995-10-21";//yyyy-MM-dd
		
		java.sql.Date sd2= java.sql.Date.valueOf(d2);
		System.out.println("SQL date 2    "+sd2);
		

	}

}
