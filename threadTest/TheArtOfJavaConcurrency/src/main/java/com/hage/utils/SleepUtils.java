package com.hage.utils;

import java.util.concurrent.TimeUnit;

/**
 * @author: Administrator
 * @date: 2019/10/7
 * Description: 让线程睡一会
 */
public class SleepUtils {
    public static final void second(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
