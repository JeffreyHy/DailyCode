package com.huang;

import java.io.IOException;
import java.nio.channels.Selector;

/**
 * 查看每个Selector占用的端口
 * Created by huangyongbo on 2017/8/13.
 */
public class SelectorTest {
    private static final int SELECTOR_SIZR=2;
    public static void  main(String [] args){
        TestUtils.printJVMPid();
        Selector [] selectors=new Selector[SELECTOR_SIZR];
        try{
            for (int i=0;i<selectors.length;i++){
                selectors[i]=Selector.open();
            }
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                for (int i=0;i<selectors.length;i++){
                    selectors[i].close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
