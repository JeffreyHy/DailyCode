package com.huang.concurrent;

/**
 * Created by JeffreyHy on 2017/10/28.
 */
public class ObjectLock {
    private String  str="111";
    public String getStr(){
        synchronized (this){
            try {
                Thread.sleep(1000);
                return str;
            } catch (InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
}
