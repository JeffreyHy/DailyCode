package com.huang.test.common;

import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by JeffreyHy on 2018/1/2.
 */
public class CommonTest extends TestCase {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testModifyString() {
        char[] arr = new char[]{'h', 'e', 'l', 'l', 'o', ' ', 'w', 'o', 'r', 'l', 'd'};
        String s = new String(arr, 0, arr.length);
        logger.info("Before modify,s:{}", s);
        arr[0] = 'a';
        logger.info("After modify,s:{}", s);//String通过数组复制创建对象，修改原char数组不影响已创建的String
    }

    @Test
    public void testDateBetween() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dispatchTm = sdf.parse("2017-08-23 23:00:53");
            Date finishTm = sdf.parse("2017-08-23 23:00:53");
            Date start = sdf.parse("2017-08-08 19:59:36");
            Date end = sdf.parse("9999-12-31 23:59:59");
            logger.info("result:{}", between(dispatchTm, start, end) && between(finishTm, start, end));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUnmodifiableSet() {
        Set<Integer> keys = new HashSet<>();
        Set<Integer> publicKeys = Collections.unmodifiableSet(keys);
        keys.add(1);
        keys.add(2);
        logger.info("publicKeys size:{}", publicKeys.size());
        publicKeys.forEach(key -> logger.info("foreach,key:{}", key));
        keys.add(3);
        logger.info("publicKeys new size:{}", publicKeys.size());
    }

    private boolean between(Date d, Date begin, Date end) {
        return d != null && begin != null && end != null && d.compareTo(begin) >= 0 && d.compareTo(end) <= 0;
    }
}
