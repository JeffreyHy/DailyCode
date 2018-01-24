package com.huang.concurrent;

/**
 * Created by JeffreyHy on 2017/10/28.
 */
public class ObjectLock {
    private String str1 = "111";
    private static String str2 = "111";

    public String getStr1() {
        synchronized (this) {
            try {
                Thread.sleep(1000);
                return str1;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public synchronized static String getStr2()  {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return str2;
    }


}
