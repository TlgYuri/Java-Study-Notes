package cn.yurihentai.java.concurrent.lock;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Yuri
 * @date 2021/12/21 11:45:14
 */
public class MyCache {

    Map<String, Integer> map = new ConcurrentHashMap<>();

    public void insert(String key, Integer value) {
        System.err.println("线程【" + Thread.currentThread().getName() + "】正在写入--");
        map.put(key, value);
    }

    public Integer select(String key) {
        System.err.println("线程【" + Thread.currentThread().getName() + "】正在读取++");
        return map.get(key);
    }
}