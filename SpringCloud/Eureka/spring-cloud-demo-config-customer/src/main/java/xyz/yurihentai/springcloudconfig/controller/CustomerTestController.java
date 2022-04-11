package xyz.yurihentai.springcloudconfig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RefreshScope // 刷新配置
public class CustomerTestController {

    private static final String REQUEST_URL = "http://cloud-config-center";

    @Value("${config.info:error}")
    private String configInfo;
    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/test")
    public String test() {
        return applicationName + " " + configInfo;
    }

    @GetMapping("/testCenter")
    public String getCenter() {
        return restTemplate.getForObject(REQUEST_URL + "/test", String.class);
    }

}