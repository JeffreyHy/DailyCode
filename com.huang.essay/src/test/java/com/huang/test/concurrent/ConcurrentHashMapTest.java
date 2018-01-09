package com.huang.test.concurrent;

import com.huang.concurrent.MissionHolder;
import com.huang.concurrent.MissionInfo;
import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by huangyongbo on 2018/1/9.
 */
public class ConcurrentHashMapTest extends TestCase {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testConcurrentMissionHolder() throws InterruptedException {
        Thread t = new Thread(() -> {
            MissionInfo info = MissionHolder.getAndUpdate(1);
            if (info != null) {
                logger.info("missionId:{},name:{},",info.getMissionId(), info.getName());
            }
        });
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                MissionHolder.addMission(new MissionInfo(i, "" + i));
            }
        });
        Thread t2 = new Thread(() -> {
            MissionInfo info = MissionHolder.putIfAbsent(2);
            if (info != null) {
                logger.info("missionId:{},name:{},", info.getMissionId(), info.getName());
            }
        });
        t.start();
        t1.start();
        t2.start();
        t.join();
        t1.join();
        t2.join();
    }
}
