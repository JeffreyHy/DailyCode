package com.huang;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class DateTest {
	public static void main(String[] args) {
		System.out.println(new Date(1497358800000L));
		System.out.println(new Date(1497359700000L));
		System.out.println(new Date(1497360600000L));
		System.out.println(new Date(1497362400000L));
		System.out.println(new Date(1497358800000L));
		System.out.println(new Date(1497364800000L));
		
		System.out.println(getTimeIntersection());
		
		/*System.out.println(new Date(1497369600000L));
		System.out.println(new Date(1497382200000L));
		System.out.println(new Date(1497394800000L));
		System.out.println(new Date(1497407400000L));
		System.out.println(getTimeIntersection1());*/
		
		
		System.out.println(new Date(1497394800000L));
		System.out.println(new Date(1497407400000L));
		System.out.println(new Date(1497445931000L));
		System.out.println(new Date(1497449160000L));
		System.out.println(new Date(1497451984000L));
		System.out.println(new Date(1497452340000L));
		System.out.println(new Date(1497445200000L));
		System.out.println(new Date(1497492000000L));
		System.out.println(getTimeIntersection2());
		
		System.out.println(new Date(1497452340000L));
		System.out.println(new Date(1497454140000L));
		System.out.println(new Date(1497445200000L));
		System.out.println(new Date(1497492000000L));
		System.out.println(getTimeIntersection3());
		
		System.out.println(new Date(1497454140000L));
		System.out.println(new Date(1497455040000L));
		System.out.println(new Date(1497445200000L));
		System.out.println(new Date(1497492000000L));
		System.out.println(getTimeIntersection4());
		
		System.out.println(new Date(1497455040000L));
		System.out.println(new Date(1497455940000L));
		System.out.println(new Date(1497445200000L));
		System.out.println(new Date(1497492000000L));
		System.out.println(getTimeIntersection5());
		
		System.out.println(new Date(1497455040000L));
		System.out.println(new Date(1497455940000L));
		
		/*Date a = new Date();
		Date b = new Date();
		System.out.println("a.before(a)-->" + a.before(a));
		System.out.println("a.after(a)-->" + a.after(a));
		System.out.println("a.before(b)-->" + a.before(b));
		System.out.println("a.after(b)-->" + a.after(b));
		
		@SuppressWarnings("deprecation")
		Date currentTime = new Date(2017,05,23,20,0,0);
		@SuppressWarnings("deprecation")
		Date publishTime = new Date(2017,05,23,20,22,0);
		
		@SuppressWarnings("deprecation")
		Date startTime = new Date(2017,05,23,20,32,0);
		System.out.println("d.before(c)-->" + publishTime.before(currentTime));
		System.out.println("d.after(c)-->" + publishTime.after(currentTime));
		System.out.println("c.before(d)-->" + currentTime.before(publishTime));
		System.out.println("c.after(d)-->" + currentTime.after(publishTime));
		
		System.out.println("c.before(d)-->" + currentTime.before(startTime));
		System.out.println("c.after(d)-->" + currentTime.after(startTime));
		
		Date d = new Date(2017,05,17,13,00,00);
		Date s = new Date(2017,05,17,11,00,00);
		System.out.println(between(d,s,d));*/
	}
	
	private static boolean between(Date d, Date begin, Date end) {
		return d != null && begin != null && end != null
				&& d.compareTo(begin) >= 0 && d.compareTo(end) <= 0;
	}
	public static int getTimeIntersection() {
        Set<Long> attendanceTime = new HashSet<Long>(2 << 10);// 初始化容量 2 << 10
        Set<Long> missionTime = new HashSet<Long>(2 << 15);// 初始化容量 2 << 15
        long start = 1497358800000L / 1000;
        long end = 1497359700000L / 1000;
        for (long i = start; i < end; i++) {
            attendanceTime.add(i);
        }
        
        long start1 = 1497360600000L / 1000;
        long end1 = 1497362400000L / 1000;
        for (long i = start1; i < end1; i++) {
        	attendanceTime.add(i);
        }
        
        long start2 = 1497358800000L / 1000;
        long end2 = 1497364800000L / 1000;
        for (long i = start2; i < end2; i++) {
            missionTime.add(i);
        }
        
        attendanceTime.retainAll(missionTime);
        return attendanceTime.size();
    }
	
	public static int getTimeIntersection1() {
        Set<Long> attendanceTime = new HashSet<Long>(2 << 10);// 初始化容量 2 << 10
        Set<Long> missionTime = new HashSet<Long>(2 << 15);// 初始化容量 2 << 15
        long start = 1497369600000L / 1000;
        long end = 1497382200000L / 1000;
        for (long i = start; i < end; i++) {
            attendanceTime.add(i);
        }
   
        long start2 = 1497394800000L / 1000;
        long end2 = 1497407400000L / 1000;
        for (long i = start2; i < end2; i++) {
            missionTime.add(i);
        }
        
        attendanceTime.retainAll(missionTime);
        return attendanceTime.size();
    }
	
	public static int getTimeIntersection2() {
        Set<Long> attendanceTime = new HashSet<Long>(2 << 10);// 初始化容量 2 << 10
        Set<Long> missionTime = new HashSet<Long>(2 << 15);// 初始化容量 2 << 15
        long start = 1497394800000L / 1000;
        long end = 1497407400000L / 1000;
        for (long i = start; i < end; i++) {
            attendanceTime.add(i);
        }
        
        long start1 = 1497445931000L / 1000;
        long end1 = 1497449160000L / 1000;
        for (long i = start1; i < end1; i++) {
        	attendanceTime.add(i);
        }
        
        long start2 = 1497451984000L / 1000;
        long end2 =1497452340000L / 1000;
        for (long i = start2; i < end2; i++) {
        	attendanceTime.add(i);
        }
        
        long start3 = 1497445200000L / 1000;
        long end3 =1497492000000L / 1000;
        for (long i = start3; i < end3; i++) {
            missionTime.add(i);
        }
        attendanceTime.retainAll(missionTime);
        return attendanceTime.size();
    }
	
	public static int getTimeIntersection3() {
        Set<Long> attendanceTime = new HashSet<Long>(2 << 10);// 初始化容量 2 << 10
        Set<Long> missionTime = new HashSet<Long>(2 << 15);// 初始化容量 2 << 15
        long start = 1497452340000L / 1000;
        long end =1497454140000L / 1000;
        for (long i = start; i < end; i++) {
            attendanceTime.add(i);
        }
   
        long start2 = 1497445200000L / 1000;
        long end2 = 1497492000000L / 1000;
        for (long i = start2; i < end2; i++) {
            missionTime.add(i);
        }
        
        attendanceTime.retainAll(missionTime);
        return attendanceTime.size();
    }
	
	public static int getTimeIntersection4() {
        Set<Long> attendanceTime = new HashSet<Long>(2 << 10);// 初始化容量 2 << 10
        Set<Long> missionTime = new HashSet<Long>(2 << 15);// 初始化容量 2 << 15
        long start = 1497454140000L / 1000;
        long end =1497455040000L / 1000;
        for (long i = start; i < end; i++) {
            attendanceTime.add(i);
        }
   
        long start2 = 1497445200000L / 1000;
        long end2 = 1497492000000L / 1000;
        for (long i = start2; i < end2; i++) {
            missionTime.add(i);
        }
        
        attendanceTime.retainAll(missionTime);
        return attendanceTime.size();
    }
	
	public static int getTimeIntersection5() {
        Set<Long> attendanceTime = new HashSet<Long>(2 << 10);// 初始化容量 2 << 10
        Set<Long> missionTime = new HashSet<Long>(2 << 15);// 初始化容量 2 << 15
        long start = 1497455040000L / 1000;
        long end =1497455940000L / 1000;
        for (long i = start; i < end; i++) {
            attendanceTime.add(i);
        }
   
        long start2 = 1497445200000L / 1000;
        long end2 = 1497492000000L / 1000;
        for (long i = start2; i < end2; i++) {
            missionTime.add(i);
        }
        
        attendanceTime.retainAll(missionTime);
        return attendanceTime.size();
    }
}
