package com.huang;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * Created by JeffreyHy on 2017/8/13.
 */
public class SelectorWakeupTest {

    public static void main(String[] args) {
        TestUtils.printJVMPid();
        try {
            Selector selector=Selector.open();
            Thread thread=new Thread(() -> {
                try {
                    Thread.sleep(20000);
                    System.out.println("Call wakeup() start");
                    selector.wakeup();
                    System.out.println("Call wakeup() end");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            thread.start();
            System.out.println("Call select() start");
            selector.select();
            System.out.println("Call select() end");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
