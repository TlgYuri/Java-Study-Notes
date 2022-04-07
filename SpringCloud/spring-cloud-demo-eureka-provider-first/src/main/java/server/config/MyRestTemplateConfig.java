package server.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description：
 * @author： Yuri
 * @date： 2022/4/6 11:45:14
 */
@Configuration
public class MyRestTemplateConfig {

    @Bean
    @LoadBalanced //如果被调用方部署了多个集群节点，则需要通过此注解开启负载均衡，否则RestTemplate无法决定调用同名服务的哪一个
    public MyRestTemplateConfig restTemplate() {
        return new MyRestTemplateConfig();
    }

}