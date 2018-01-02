package com.huang.util;

import java.lang.management.ManagementFactory;

/**
 * Created by JeffreyHy on 2018/1/2.
 */
public final class JVMUtil {
    private JVMUtil() {
    }

    public static String getJVMPid() {
        // get name representing the running Java virtual machine.
        String name = ManagementFactory.getRuntimeMXBean().getName();
        // get pid
        if (name.indexOf("@") != -1) {
            return name.substring(0,name.indexOf("@"));
        }
        return null;
    }
}
