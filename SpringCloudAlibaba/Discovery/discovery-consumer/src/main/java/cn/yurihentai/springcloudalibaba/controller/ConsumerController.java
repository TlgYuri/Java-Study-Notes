package cn.yurihentai.springcloudalibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsumerController {

    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "consumer")
    public String consumer() {
        return "nacos consumer, port: " + serverPort;
    }

}