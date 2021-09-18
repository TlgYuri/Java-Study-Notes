package xyz.yurihentai.bean;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitBean implements InitializingBean {

    public static String APP_NAME;
    public static String APP_AUTHOR;
    public static String AUTHOR_ADDRESS;
    public static List<String> APP_ARRAY;

    // @Value从配置文件注入值  ":"表示设置默认值
    @Value("${app.name:SSM}")
    private String appName;
    @Value("${app.author}")
    private String appAuthor;
    @Value("${author.address}")
    private String authorAddress;
    // 将配置文件中的值通过指定分隔符分割为数组或集合注入
    @Value("#{${app.array}.split(',')}")
    private List<String> appArray;

    @Override
    public void afterPropertiesSet() throws Exception {
        APP_NAME = this.appName;
        APP_AUTHOR = this.appAuthor;
        AUTHOR_ADDRESS = this.authorAddress;
        APP_ARRAY = this.appArray;
        System.out.println("Bean:InitializingBean, Initialized!");
    }

}