package xyz.yurihentai.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.yurihentai.util.RedisUtil;

@RestController

public class RedisTestController {

    private final RedisUtil redisUtil;

    @Autowired
    public RedisTestController(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @PostMapping("testSet")
    public Boolean testSet(@RequestParam String key, @RequestParam String value) {
        return redisUtil.set(key,value);
    }

    @GetMapping("testGet")
    public String testGet(@RequestParam String key) {
        return redisUtil.get(key);
    }

}
