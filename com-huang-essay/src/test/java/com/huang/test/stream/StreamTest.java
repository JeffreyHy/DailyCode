package com.huang.test.stream;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by JeffreyHy on 2018/1/15.
 */
public class StreamTest extends TestCase {
    @Test
    public void testStreamPeek() {
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());
    }
}
