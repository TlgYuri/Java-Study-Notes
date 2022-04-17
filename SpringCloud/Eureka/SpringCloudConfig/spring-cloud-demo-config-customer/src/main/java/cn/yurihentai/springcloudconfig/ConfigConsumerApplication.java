package cn.yurihentai.springcloudconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ConfigConsumerApplication {

    public static void main(String args[]) {
        SpringApplication.run(ConfigConsumerApplication.class,args);
    }

}