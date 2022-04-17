package cn.yurihentai.springcloud.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

// 开启eureka注册中心
@EnableEurekaServer
@SpringBootApplication
public class EurekaRegistryApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaRegistryApplication.class, args);
    }

}
