package com.demo.api.account;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test2 {

	public static void main(String[] args) {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh.mm.ss");
		java.util.Date utilDate = new Date();
		System.out.println(utilDate);
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());//2019-05-25 12:42
		System.out.println(sqlDate);
		java.sql.Time sTime = new java.sql.Time(utilDate.getTime());
		System.out.println(sTime);
		java.sql.Timestamp stp = new java.sql.Timestamp(utilDate.getTime());
		System.out.println(stp);

		String a = f.format(utilDate);
		System.out.println("a: "+a);

		java.sql.Date sqlDate2 = java.sql.Date.valueOf("2005-12-12");

		java.util.Date utilDate2 = new java.util.Date(sqlDate2.getTime());
		System.out.println("sqlDate2----"+sqlDate2);
		System.out.println(f.format(sqlDate2));
		
		//获得 年
		SimpleDateFormat sy=new SimpleDateFormat("yyyy");//yyyy 2019 //YYYY 2019 //yy 19
		System.out.println(sy.format(sqlDate));
		
		//获得 月
		SimpleDateFormat sm=new SimpleDateFormat("mm");//MM 05 mm 42
		System.out.println(sm.format(sqlDate));
		
		//获得 日
		SimpleDateFormat sd=new SimpleDateFormat("dd");	//dd 25 DD 145
		System.out.println(sd.format(sqlDate));
		
		//要想12小时制就小写hh:mm:ss，要想24小时制就大写HH:mm:ss
		
		
		//String to Date
		SimpleDateFormat formatter=new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr="20190318";
		try {
			Date date=formatter.parse(dateStr);
			System.out.println(formatter.format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//
		System.out.println(new java.sql.Date(System.currentTimeMillis()));
	}
	}


