package cn.yurihentai.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

@Component
public class RedisUtil {

    private final JedisPool jedisPool;

    @Autowired
    public RedisUtil(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public Jedis getJedis() {
        return jedisPool.getResource();
    }

    public boolean set(String key, String value) {
        Jedis jedis = this.getJedis();
        String result = jedis.set(key, value);
        jedis.close();
        return "OK".equals(result);
    }

    public boolean setex(String key, int seconds, String value) {
        Jedis jedis = this.getJedis();
        String result = jedis.setex(key, seconds, value);
        jedis.close();
        return "OK".equals(result);
    }

    public String get(String key) {
        Jedis jedis = this.getJedis();
        String result = jedis.get(key);
        jedis.close();
        return result;
    }

    public boolean hset(String key, Map<String, String> hash) {
        Jedis jedis = this.getJedis();
        Long result = jedis.hset(key, hash);
        jedis.close();
        return result > 0;
    }

    public Map<String, String> hget(String key) {
        Jedis jedis = this.getJedis();
        Map<String, String> result = jedis.hgetAll(key);
        jedis.close();
        return result;
    }

    public boolean hset(String key, String field, String value) {
        Jedis jedis = this.getJedis();
        Long result = jedis.hset(key, field, value);
        jedis.close();
        return result > 0;
    }

    public String hget(String key, String field) {
        Jedis jedis = this.getJedis();
        String result = jedis.hget(key, field);
        jedis.close();
        return result;
    }

}
