package cn.yurihentai.springcloudalibaba.seata;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// seata1.1版本后可以使用注解开启DataSourceProxy, 之前版本需要手动写DataSourceProxy配置 使seata管理数据源
@EnableAutoDataSourceProxy
@MapperScan("cn.yurihentai.springcloudalibaba.seata.mapper")
@EnableDiscoveryClient
@EnableFeignClients
// 需要让seata代理数据源 因此取消数据源的自动配置
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SeataOrderServiceApplication {

    public static void main(String args[]) {
        SpringApplication.run(SeataOrderServiceApplication.class,args);
    }

}