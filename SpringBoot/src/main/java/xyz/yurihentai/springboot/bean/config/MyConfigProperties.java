package xyz.yurihentai.springboot.bean.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// 将配置文件中的yuri开头的配置读取到Java Bean中，在配置类中使用@EnableConfigurationProperties引入
// 如果要在配置文件中有配置提示，需要引入依赖 spring-boot-configuration-processor
@ConfigurationProperties(prefix = "yuri")
@Component
public class MyConfigProperties {

    private String name;

    private String age;

    private String hobby;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "MyConfigProperties{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}