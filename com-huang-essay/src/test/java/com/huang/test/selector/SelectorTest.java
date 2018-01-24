package com.huang.test.selector;

import com.huang.util.JVMUtil;
import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * Created by JeffreyHy on 2018/1/2.
 */
public class SelectorTest extends TestCase {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testWakeupSelector() throws IOException {
        logger.info("pid:{}", JVMUtil.getJVMPid());
        Selector selector = Selector.open();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
                logger.info("Call wakeup() start");
                selector.wakeup();
                logger.info("Call wakeup() end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        logger.info("Call select() start");
        selector.select();
        logger.info("Call select() end");
    }
}
