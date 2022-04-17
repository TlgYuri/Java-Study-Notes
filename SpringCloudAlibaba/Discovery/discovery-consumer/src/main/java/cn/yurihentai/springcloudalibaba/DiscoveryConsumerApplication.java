package cn.yurihentai.springcloudalibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DiscoveryConsumerApplication {

    public static void main(String args[]) {
        SpringApplication.run(DiscoveryConsumerApplication.class,args);
    }

}