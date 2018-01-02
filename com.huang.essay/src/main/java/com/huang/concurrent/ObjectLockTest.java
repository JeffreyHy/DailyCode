package com.huang.concurrent;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by JeffreyHy on 2017/10/28.
 */
public class ObjectLockTest {
    public static void main(String[] args) throws IllegalAccessException, InterruptedException {
        ObjectLock obj = new ObjectLock();
        Thread thread=new Thread(()->{
            System.out.println("objectLock:" + obj.getStr());
        });
        thread.start();
        System.out.println("start modify");
        Class userCla = obj.getClass();
        Field[] fs = userCla.getDeclaredFields();
        for (Field field : fs) {
            field.setAccessible(true);
            field.set(obj,"222");
        }
        System.out.println("modify finish");
        thread.join();
    }
}
