package xyz.yurihentai.cloud.consul.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @description：
 * @author： Yuri
 * @date： 2022/4/8 11:45:14
 */
@RestController
public class ConsumerController {

    private final static String REQUEST_URL = "http://cloud-consul-provider";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("consumer")
    public String consumer() {
        return "consumer";
    }

    @GetMapping("consume")
    public  String consume() {
        return restTemplate.getForObject(REQUEST_URL+"/provider", String.class);
    }

}