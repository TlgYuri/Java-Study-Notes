package cn.yurihentai.springcloud.server.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/test")
    public String test() {
        return "First";
    }

    /**
     * 发现服务信息
     * 说是需要开启@EnableDiscoveryClient，可能是新版本不需要，只要@EnableEurekaClient就行
     */
    @GetMapping("discovery")
    public void discovery() {
        System.out.println(discoveryClient.getOrder());
        System.out.println(discoveryClient.getServices());
        System.out.println(discoveryClient.getInstances("SPRING-CLOUD-PROVIDER-FIRST"));
        System.out.println(discoveryClient.description());
    }

}