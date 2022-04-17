package cn.yurihentai.springcloudalibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class DiscoveryProviderApplication {

    public static void main(String args[]) {
        SpringApplication.run(DiscoveryProviderApplication.class,args);
    }

}