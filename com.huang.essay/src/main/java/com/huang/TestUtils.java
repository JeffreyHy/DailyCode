package com.huang;

import java.lang.management.ManagementFactory;

/**
 * Created by JeffreyHy on 2017/8/13.
 */
public class TestUtils {
    public static void printJVMPid(){
        // get name representing the running Java virtual machine.
        String name = ManagementFactory.getRuntimeMXBean().getName();
        // get pid
        String pid = name.split("@")[0];
        System.out.println("Pid is:"+pid);
    }
}
