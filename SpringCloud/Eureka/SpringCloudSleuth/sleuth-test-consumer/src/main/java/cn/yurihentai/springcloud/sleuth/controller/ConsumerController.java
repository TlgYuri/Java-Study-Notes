package cn.yurihentai.springcloud.sleuth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumerController {

    private static final String REQUEST_URL = "http://localhost:8001";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("consume")
    public String consume() {
        return "consumer get message:" + restTemplate.getForObject(REQUEST_URL + "provide", String.class);
    }

}