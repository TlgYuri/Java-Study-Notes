package xyz.yurihentai.springcloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// 开启Eureka的客户端服务（主要Eureka用
@EnableEurekaClient
// 开启服务发现（所有SpringCloud微服务都可以用
//@EnableDiscoveryClient
@SpringBootApplication
public class FirstProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstProviderApplication.class, args);
    }

}