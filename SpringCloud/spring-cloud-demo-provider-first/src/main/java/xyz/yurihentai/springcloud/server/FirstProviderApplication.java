package xyz.yurihentai.springcloud.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// 将本服务注册到注册中心
@EnableDiscoveryClient
@SpringBootApplication
public class FirstProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstProviderApplication.class, args);
    }

}