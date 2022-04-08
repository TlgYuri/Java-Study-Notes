package xyz.yurihentai.cloud.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description：
 * @author： Yuri
 * @date： 2022/4/8 11:45:14
 */
@EnableDiscoveryClient
@SpringBootApplication
public class ConsulComsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulComsumerApplication.class, args);
    }

}