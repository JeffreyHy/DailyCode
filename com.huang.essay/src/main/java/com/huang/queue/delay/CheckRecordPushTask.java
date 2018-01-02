package com.huang.queue.delay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by JeffreyHy on 2018/1/2.
 */
public class CheckRecordPushTask implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(CheckRecordPushTask.class);

    private CheckNotifyManager manager;

    public CheckRecordPushTask(CheckNotifyManager manager) {
        this.manager = manager;
    }

    @Override
    public void run() {
        while (true) {
            try {
                CheckNoticeDelay delay = manager.takeNotice();
                if (delay != null) {
                    logger.info("push msg,recordId:{}", delay.getRecordId());
                }
            } catch (InterruptedException e) {
                logger.error("Thread is interrupted");
            }
        }
    }
}
