package com.huang.concurrent;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Set;

/**
 * Created by huangyongbo on 2017/9/11.
 */
public final class CacheHolder {

    private CacheHolder(){}

    private static final Map<String, Set<Integer>> userDecideScopeMap =
            Maps.newConcurrentMap();

    public static void addScopeUser(String key,Set<Integer> value){
        userDecideScopeMap.putIfAbsent(key, value);
    }
    public static boolean removeScopeUser(String key,Integer scope) {
        Set<Integer> result = userDecideScopeMap.get(key);
        if (result != null) {
            synchronized (result) {
                System.out.println("current Thread:"+Thread.currentThread());
                if(result.size()==0){
                    result.remove(scope);
                    return Boolean.FALSE;
                }
                result.remove(scope);
                if (result.isEmpty()) {
                    userDecideScopeMap.remove(key);
                }
            }
        }
        return Boolean.TRUE;
    }
    public static Set<Integer> getScopeUser(String key){
        return userDecideScopeMap.get(key);
    }

}
