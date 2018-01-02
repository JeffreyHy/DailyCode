package com.huang.test.algorithm;

import com.huang.algorithm.intersection.TimeIntersectionAlgorithm;
import junit.framework.TestCase;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by JeffreyHy on 2018/1/2.
 */
public class TimeIntersectionAlgorithmTest extends TestCase {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testTimeIntersectionAlgorithm() {
        TimeIntersectionAlgorithm algorithm = new TimeIntersectionAlgorithm();
        long start = System.currentTimeMillis();
        int result1 = algorithm.calcTimeIntersectionWithBitSet();
        logger.info("calcTimeIntersectionWithBitSet finish,result:{},cost:{}", result1, System.currentTimeMillis() - start);
        start = System.currentTimeMillis();
        int result2 = algorithm.calcTimeIntersectionWithHashSet();
        logger.info("calcTimeIntersectionWithHashSet finish,result:{},cost:{}", result2, System.currentTimeMillis() - start);
        assert (result1 == result2);
    }
}
