package xyz.yurihentai.springcloud.controller;

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

    /** 指定要调用的服务  为provider处配置的spring.application.name */
    private static final String PROVIDER_URL = "http://spring-cloud-zookeeper-provider";

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("consumer")
    public String consumer() {
        return "consumer";
    }

    @GetMapping("consume")
    public String consume() {
        return restTemplate.getForObject(PROVIDER_URL+"/provider", String.class);
    }

}