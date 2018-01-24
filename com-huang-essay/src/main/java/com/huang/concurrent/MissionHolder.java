package com.huang.concurrent;

import com.huang.atom.MissionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *  从ConcurrentHashMap取出元素，判断为空则加入，不为空则返回；推荐用putIfAbsent，否则无法保证线程安全，需要加锁
 * Created by huangyongbo on 2018/1/9.
 */
public class MissionHolder {
    private static Logger logger = LoggerFactory.getLogger(MissionHolder.class);
    private static final Map<Integer, MissionInfo> cacheMap = new ConcurrentHashMap<>();

    /**
     * 非线程安全
     *
     * @param missionId
     * @return
     */
    public static MissionInfo getAndUpdate(Integer missionId) {
        if (missionId != null) {
            MissionInfo info = cacheMap.get(missionId);
            if (info == null) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                info = new MissionInfo(missionId, "null");
                addMission(info);
            }
            return info;
        }
        return null;
    }

    /**
     * 线程安全
     *
     * @param missionId
     * @return
     */
    public static MissionInfo putIfAbsent(Integer missionId) {
        if (missionId != null) {
            MissionInfo info = cacheMap.get(missionId);
            if (info == null) {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                info = cacheMap.putIfAbsent(missionId, new MissionInfo(missionId, "null"));
            }
            return info;
        }
        return null;
    }

    /**
     * 线程安全
     *
     * @param info
     */
    public static void addMission(MissionInfo info) {
        if (info != null) {
            MissionInfo oldInfo = cacheMap.get(info.getMissionId());
            logger.info("add,missionId:{},name:{},oldName:{}", info.getMissionId(), info.getName(), oldInfo == null ? null : oldInfo.getName());
            cacheMap.put(info.getMissionId(), info);
        }
    }
}
