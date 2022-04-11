package xyz.yurihentai.springcloudconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * 文件读取顺序：如果根路径有application.yml 会作为默认配置，一定会优先读取，然后再读取目标文件  如果有相同配置会以目标文件为准，覆盖application.yml中的相同属性
 * 寻找文件方式： 分支(label) 应用名(application) 配置环境(profile)
 *      /{label}/{application}-{profile}.yml
 *      /{application}-{profile}.yml
 *      /{application}/{profile}[/{label}
 */
@EnableConfigServer // 作为配置中心
@EnableEurekaClient
@SpringBootApplication
public class ConfigCenterApplication {

    public static void main(String args[]) {
        SpringApplication.run(ConfigCenterApplication.class, args);
    }

}