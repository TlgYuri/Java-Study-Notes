package cn.yurihentai.springcloudalibaba.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MyConfig {

    @Bean
    @LoadBalanced // nacos 自带ribbon 实现了负载均衡
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

}