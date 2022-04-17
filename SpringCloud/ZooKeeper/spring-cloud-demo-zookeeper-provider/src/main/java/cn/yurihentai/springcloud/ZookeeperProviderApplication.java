package cn.yurihentai.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @description：
 * @author： Yuri
 * @date： 2022/4/7 11:45:14
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ZookeeperProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZookeeperProviderApplication.class, args);
    }

}