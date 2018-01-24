package com.huang.queue.delay;


import org.springframework.util.Assert;

import java.util.concurrent.DelayQueue;

/**
 * Created by JeffreyHy on 2018/1/2.
 */
public class CheckNotifyManager {
    private DelayQueue<CheckNoticeDelay> pushQueue = new DelayQueue();

    public boolean addNotice(CheckNoticeDelay checkNotice) {
        Assert.notNull(checkNotice);
        return pushQueue.offer(checkNotice);
    }

    public CheckNoticeDelay takeNotice() throws InterruptedException {
        return pushQueue.take();
    }
}
