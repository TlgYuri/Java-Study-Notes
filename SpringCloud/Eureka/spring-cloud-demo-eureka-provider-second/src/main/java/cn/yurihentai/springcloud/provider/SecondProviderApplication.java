package cn.yurihentai.springcloud.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// 将本服务注册到注册中心
@EnableDiscoveryClient
@SpringBootApplication
public class SecondProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondProviderApplication.class, args);
    }

}