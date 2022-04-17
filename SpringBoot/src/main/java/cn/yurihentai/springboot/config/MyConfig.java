package cn.yurihentai.springboot.config;

import cn.yurihentai.springboot.bean.config.MyConfigProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

// 开启配置绑定功能 引入指定对象
@EnableConfigurationProperties({MyConfigProperties.class})
@Configuration
public class MyConfig {


}