package com.huang.test.concurrent;

import com.huang.util.DateUtil;
import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author JeffreyHy
 * @date Created by  2018/3/8 10:10
 */
public class DateFormatTest extends TestCase {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final DateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 测试DateFormat线程安全性
     * 场景模拟：100个线程对other进行格式化，main线程调用getBetweenDates，由于SimpleDateFormat线程不安全，
     * 高并发时，可能导致start读到other的数据，导致main线程陷入死循环
     *
     * 修改方法：1 SimpleDateFormat改为局部变量，缺点是每次调用都会创建一个SimpleDateFormat
     * 2 使用ThreadLocal: 每个线程都将拥有自己的SimpleDateFormat对象副本。
    */
    @Test
    public void testDataFormat() {
        Date start = DateUtil.parse("2018.01.08", "yyyy.MM.dd");
        Date end = DateUtil.parse("2018.01.10", "yyyy.MM.dd");
        final Date other = DateUtil.parse("2018.01.12", "yyyy.MM.dd");
        final CountDownLatch waitLatch = new CountDownLatch(1);//控制100个线程同一时间发起请求，加大start被修改的可能性
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                try {
                    waitLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                formatDate(other);
            }).start();
        }
        waitLatch.countDown();
        getBetweenDates(formatDate(start), formatDate(end));
    }

    private static String formatDate(Date date) {
        return DEFAULT_DATE_FORMAT.format(date);
    }

    /**
     * 当程序陷入死循环时，会不断创建newDate，导致内存被打爆
     * @param begin
     * @param end
     * @return
     */
    private List<String> getBetweenDates(String begin, String end) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        List<String> betweenList = new ArrayList<>();
        try {
            Calendar startDay = Calendar.getInstance();
            startDay.setTime(format.parse(begin));
            startDay.add(Calendar.DATE, -1);
            while (true) {
                //每循环一次，startDay加一天，当begin小于end时，能够保证while循环正常退出
                //当begin大于end时。while就会陷入死循环
                //并发测试如果main线程陷入死循环，说明start被子线程修改为other了
                logger.info("currentThread:{}", Thread.currentThread());
                startDay.add(Calendar.DATE, 1);
                Date newDate = startDay.getTime();
                String newEnd = format.format(newDate);
                betweenList.add(newEnd);
                if (end.equals(newEnd)) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        betweenList.remove(begin);
        betweenList.remove(end);
        return betweenList;
    }
}
