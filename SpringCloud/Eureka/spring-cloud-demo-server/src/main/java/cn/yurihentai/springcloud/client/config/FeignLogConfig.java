package cn.yurihentai.springcloud.client.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignLogConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        // NONE: 不打印
        // BASIC: 仅记录请求方法、URL、响应状态码及执行时间
        // HEADERS: 在 BASIC 级别之上，追加打印 请求头和响应头的信息
        // FULL: 在HEADERS级别之上，追加打印 请求和响应的 正文及元数据
        return Logger.Level.FULL;
    }

}