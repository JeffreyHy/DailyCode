package com.huang.test.concurrent;

import com.huang.atom.MissionInfo;
import com.huang.concurrent.ImmutableObject;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 不可变对象测试，验证集合中对象为引用类型时，复制集合是否需要对集合中的对象进行复制
 * Created by JeffreyHy on 2018/1/12.
 */
public class ImmutableObjectTest extends TestCase {

    @Test
    public void testImmutableObject(){
        Map<Integer, MissionInfo> tMap = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            tMap.put(i,new MissionInfo(i,String.valueOf(i)));
        }
        ImmutableObject  io=new ImmutableObject(tMap);
        io.printMap();
        Map<Integer, MissionInfo> ioMap=io.getMap();
        for (int i = 0; i < 5; i++) {
            MissionInfo info=ioMap.get(i);
            info.setMissionId(-1);
            ioMap.replace(i,info);
        }
        io.printMap();
    }
}
