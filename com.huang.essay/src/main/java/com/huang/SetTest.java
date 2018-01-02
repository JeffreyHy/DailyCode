package com.huang;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by JeffreyHy on 2017/8/27.
 */
public class SetTest {
    public static void main(String[] args) {
        Set<Integer> keys=new HashSet<>();
        Set<Integer> publicKeys= Collections.unmodifiableSet(keys);
        keys.add(1);
        keys.add(2);
        System.out.println("publicKeys size is "+publicKeys.size());
        for(int key:publicKeys){
            System.out.println("key:"+key);
        }
        keys.add(3);
        System.out.println("publicKeys new size is "+publicKeys.size());
    }
}
