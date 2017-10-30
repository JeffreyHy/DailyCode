package com.huang;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest1 {
	public static void main(String[] args) {
		System.out.println(new Date(1497540540809L));

		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date dispatchTm=sdf.parse("2017-08-23 23:00:53");
			Date finishTm=sdf.parse("2017-08-23 23:00:53");
			Date start=sdf.parse("2017-08-08 19:59:36");
			Date end=sdf.parse("9999-12-31 23:59:59");
			System.out.println(between(dispatchTm,start,end)&&between(finishTm,start,end));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public static boolean between(Date d, Date begin, Date end) {
		return d != null && begin != null && end != null && d.compareTo(begin) >= 0 && d.compareTo(end) <= 0;
	}
}
