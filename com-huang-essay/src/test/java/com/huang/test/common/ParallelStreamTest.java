package com.huang.test.common;

import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by JeffreyHy on 2018/1/2.
 */
public class ParallelStreamTest extends TestCase {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final int COUNT = 1000;

    @Test
    public void testListAddWithParallelStream() {
        List<RiderDto> oriList = new ArrayList<>();
        for (int i = 0; i < COUNT; i++) {
            oriList.add(init());
        }
        final List<RiderDto> copeList = new ArrayList<>();
        oriList.parallelStream().forEach(rider -> {
            RiderDto t = new RiderDto();
            t.setId(rider.getId());
            t.setCityId(rider.getCityId());
            copeList.add(t);
        });
        logger.info("oriList size:{}", oriList.size());
        logger.info("copeList size:{}" + copeList.size());
        logger.info("compare copeList and oriList,result:{}", copeList.size() == oriList.size());
    }

    private RiderDto init() {
        RiderDto t = new RiderDto();
        Random random = new Random();
        t.setId(random.nextInt(2 ^ 20));
        t.setCityId(random.nextInt(1000));
        return t;
    }

    static class RiderDto implements Serializable {
        private static final long serialVersionUID = 1;
        //城市Id
        private Integer cityId;
        //骑手Id
        private Integer id;

        public Integer getCityId() {
            return cityId;
        }

        public void setCityId(Integer cityId) {
            this.cityId = cityId;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }
    }
}
