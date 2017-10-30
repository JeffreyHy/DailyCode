package com.huang.concurrent;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by huangyongbo on 2017/9/11.
 */
public class CacheHoldTest {
    public static void main(String[] args) {
        //testGetScopeUser();
        testRemove();
    }

    public static void testGetScopeUser(){
        Set<Integer> set= Sets.newConcurrentHashSet();
        String key="111";
        set.add(Integer.parseInt(key));
        CacheHolder.addScopeUser(key,set);
        final Set<Integer> mainSet=CacheHolder.getScopeUser(key);
        Thread thread=new Thread(()->{
            Set<Integer> cSet=CacheHolder.getScopeUser(key);
            System.out.println("compare mainSet and cSet,the result is "
                    +(mainSet.size()==cSet.size()));
            CacheHolder.removeScopeUser(key,Integer.valueOf(key));
            System.out.println("mainSet.size:"+mainSet.size());
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void testRemove(){
        final boolean[] flag = {true};
        while(flag[0]){
            Set<Integer> set= Sets.newConcurrentHashSet();
            String key="111";
            set.add(Integer.parseInt(key));
            CacheHolder.addScopeUser(key,set);
            Thread thread=new Thread(()->{
                flag[0] =CacheHolder.removeScopeUser(key,Integer.valueOf(key));
                System.out.println("thread remove");
            });
            Thread thread1=new Thread(()->{
                flag[0]=CacheHolder.removeScopeUser(key,Integer.valueOf(key));
                System.out.println("thread1 remove");
            });
            thread.start();
            thread1.start();
            flag[0]=CacheHolder.removeScopeUser(key,Integer.valueOf(key));
            System.out.println("main remove");
        }

    }
}
