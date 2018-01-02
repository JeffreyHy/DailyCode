package com.huang.test.concurrent;

import com.google.common.collect.Sets;
import com.huang.concurrent.CacheHolder;
import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Created by JeffreyHy on 2018/1/2.
 */
public class CacheHoldTest  extends TestCase {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Test
    public  void testGetScopeUser(){
        Set<Integer> set= Sets.newConcurrentHashSet();
        String key="111";
        set.add(Integer.parseInt(key));
        CacheHolder.addScopeUser(key,set);
        final Set<Integer> mainSet=CacheHolder.getScopeUser(key);
        Thread thread=new Thread(()->{
            Set<Integer> cSet=CacheHolder.getScopeUser(key);
            logger.info("compare mainSet and cSet,the result is {}",(mainSet.size()==cSet.size()));
            CacheHolder.removeScopeUser(key,Integer.valueOf(key));
            logger.info("mainSet.size:{}",mainSet.size());
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public  void testRemove(){
        final boolean[] flag = {true};
        while(flag[0]){
            Set<Integer> set= Sets.newConcurrentHashSet();
            String key="111";
            set.add(Integer.parseInt(key));
            CacheHolder.addScopeUser(key,set);
            Thread thread=new Thread(()->{
                flag[0] =CacheHolder.removeScopeUser(key,Integer.valueOf(key));
                logger.info("thread remove");
            });
            Thread thread1=new Thread(()->{
                flag[0]=CacheHolder.removeScopeUser(key,Integer.valueOf(key));
                logger.info("thread1 remove");
            });
            thread.start();
            thread1.start();
            flag[0]=CacheHolder.removeScopeUser(key,Integer.valueOf(key));
            logger.info("main remove");
        }

    }
}
