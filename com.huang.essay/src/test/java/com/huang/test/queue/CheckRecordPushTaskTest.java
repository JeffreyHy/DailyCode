package com.huang.test.queue;

import com.huang.queue.delay.CheckNoticeDelay;
import com.huang.queue.delay.CheckNotifyManager;
import com.huang.queue.delay.CheckRecordPushTask;
import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by JeffreyHy on 2018/1/2.
 */
public class CheckRecordPushTaskTest extends TestCase {
    @Test
    public void testPushNotice() {
        CheckNotifyManager manager = new CheckNotifyManager();
        manager.addNotice(new CheckNoticeDelay(1, System.currentTimeMillis() + 1 * 1000));//1s
        manager.addNotice(new CheckNoticeDelay(2, System.currentTimeMillis() + 6 * 1000));//6s
        manager.addNotice(new CheckNoticeDelay(3, System.currentTimeMillis() + 3 * 1000));//3s
        CheckRecordPushTask task = new CheckRecordPushTask(manager);
        Thread thread = new Thread(task);
        thread.start();
        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
