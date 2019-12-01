package com.hage.ConcurrencyProgramming.ReadWriteLock;



import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author: Administrator
 * @date: 2019/11/23
 * Description: 用读写锁实现线程安全的hashMap
 */
public class Cache {
    static Map<String,Object> map = new HashMap<>();
    /**
     * 获取读写锁
     */
    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    /**
     * 获取读锁
     */
    static Lock r= rwl.readLock();
    /**
     * 获取写锁
     */
    static Lock w = rwl.writeLock();

    /**
     * 获取一个key对应的value
     * @param key
     * @return
     */
    public static final Object get(String key){
        r.lock();
        try {
            return map.get(key);
        }finally {
            r.unlock();
        }
    }

    /**
     * 设置key对应的value，并返回旧的value值
     * @param key
     * @param value
     * @return
     */
    public static final Object set(String key,Object value){
        w.lock();
        try {
            return map.put(key, value);
        }finally {
            w.unlock();
        }
    }

}
