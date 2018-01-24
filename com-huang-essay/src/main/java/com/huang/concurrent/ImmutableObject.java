package com.huang.concurrent;

import com.huang.atom.MissionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by JeffreyHy on 2018/1/12.
 */
public final class ImmutableObject {
    private static Logger logger = LoggerFactory.getLogger(ImmutableObject.class);
    private final Map<Integer, MissionInfo> map;

    public ImmutableObject(Map<Integer, MissionInfo> tMap) {
        Map<Integer, MissionInfo> finalMap = new HashMap<>();
        tMap.entrySet().forEach(entry -> {
            finalMap.put(entry.getKey(), entry.getValue());
        });
        this.map = finalMap;
    }

    public Map<Integer, MissionInfo> getMap() {
        Map<Integer, MissionInfo> finalMap = new HashMap<>();
        this.map.entrySet().forEach(entry -> {
            finalMap.put(entry.getKey(), entry.getValue());
        });
        return finalMap;
    }

    public void printMap() {
        this.map.entrySet().forEach(entry -> {
            logger.info("key:{},missionId:{}", entry.getKey(), entry.getValue().getMissionId());
        });
    }
}
