package cn.yurihentai.springcloudalibaba.sentinel.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelServiceController {

    @GetMapping("testA")
    public String testA() {
        return "Test Sentinel A";
    }

    @GetMapping("testB")
    public String testB() {
        return "Test Sentinel B";
    }

}