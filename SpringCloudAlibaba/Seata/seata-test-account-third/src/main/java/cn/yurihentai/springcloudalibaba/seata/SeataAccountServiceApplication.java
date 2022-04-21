package cn.yurihentai.springcloudalibaba.seata;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableAutoDataSourceProxy
@MapperScan("cn.yurihentai.springcloudalibaba.seata.mapper")
@EnableDiscoveryClient
@EnableFeignClients
// 需要让seata代理数据源 因此取消数据源的自动配置
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SeataAccountServiceApplication {

    public static void main(String args[]) {
        SpringApplication.run(SeataAccountServiceApplication.class,args);
    }

}