package com.huang;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * Created by huangyongbo on 2017/10/9.
 */
public class CollectionSortTest {
    public static void main(String[] args) {
        List<Integer> list= Lists.newArrayList(3,2,5,7);
        Collections.sort(list);
        list.forEach(a->System.out.println(a));
    }
}
