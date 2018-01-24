package com.huang.test.concurrent;

import com.huang.concurrent.ObjectLock;
import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * Created by JeffreyHy on 2018/1/2.
 */
public class ObjectLockTest extends TestCase {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testConcurrentModifyObjectLockAttr() throws IllegalAccessException, InterruptedException {
        ObjectLock obj = new ObjectLock();
        Thread thread = new Thread(() -> logger.info("getStr:{}", obj.getStr1()));
        thread.start();
        Thread thread1 = new Thread(() -> logger.info("getStr:{}", ObjectLock.getStr2()));
        thread1.start();
        logger.info("start modify");
        Class userCla = obj.getClass();
        Field[] fs = userCla.getDeclaredFields();
        for (Field field : fs) {
            field.setAccessible(true);
            field.set(obj, "222");
        }
        logger.info("modify finish");
        thread.join();
        thread1.join();
    }
}
