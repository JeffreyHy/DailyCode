package com.huang.concurrent;

/**
 * Created by JeffreyHy on 2017/11/25.
 */
public class StringTest {
    public static void main(String[] args) {
        char[] arr = new char[] {'h', 'e', 'l', 'l', 'o', ' ', 'w', 'o', 'r', 'l', 'd'};
        String s = new String(arr,0, arr.length);
        arr[0] = 'a';
        System.out.println(s);
    }
}
