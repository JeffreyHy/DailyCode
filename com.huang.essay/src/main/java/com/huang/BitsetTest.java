package com.huang;

import java.util.BitSet;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class BitsetTest {
	public static void main(String[] args) {
		System.out.println(new Date(1497358800000L));
		System.out.println(new Date(1497359700000L));
		System.out.println(new Date(1497360600000L));
		System.out.println(new Date(1497362400000L));
		System.out.println(new Date(1497358800000L));
		System.out.println(new Date(1497364800000L));
		System.out.println(System.currentTimeMillis());
		System.out.println(getTimeIntersection());
		System.out.println(getTimeIntersectionB());
	}
	
	public static int getTimeIntersection() {
		long starts = System.currentTimeMillis(); 
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
        long cost = System.currentTimeMillis() - starts;
        System.out.println("---------"+cost);
        return attendanceTime.size();
    }
	
	public static int getTimeIntersectionB(){
		long startt = System.currentTimeMillis(); 
		BitSet attendanceTime=new BitSet(); 
		BitSet missionTime=new BitSet();
		
		long start =1497358800000L / 1000;
		long end =  1497359700000L / 1000;
        System.out.println(end);
        int starts=(int) start;
        int ends=(int) end;
        for (int i =starts; i < ends; i++) {
            attendanceTime.set(i);
        }
        
        long start1 =1497360600000L / 1000;
        long end1 =1497362400000L / 1000;
        starts=(int) start1;
        ends=(int) end1;
        for (int i = starts; i < ends; i++) {
        	attendanceTime.set(i);
        }
        
        long start2 =1497358800000L / 1000;
        long end2 = 1497364800000L / 1000;
        starts=(int) start2;
        ends=(int) end2;
        for (int i = starts; i < ends; i++) {
            missionTime.set(i);
        }
        attendanceTime.and(missionTime);
        long cost = System.currentTimeMillis() - startt;
        System.out.println("---------"+cost);        
        return attendanceTime.cardinality();
	}
}
