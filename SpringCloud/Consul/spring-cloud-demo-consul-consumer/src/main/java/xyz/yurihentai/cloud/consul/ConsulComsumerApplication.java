package xyz.yurihentai.cloud.consul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import xyz.yurihentai.cloud.consul.ribbonrule.MyRule;

/**
 * @ComponentScan
 * 修改默认的包扫描规则，排除自定义的ribbon轮询规则
 * 此处用于指定调用特定服务时的轮询规则
 * 如果不排除，会默认替换所有负载均衡时的默认规则（默认规则为轮询）
 *
 * @RibbonClient
 * configuration:指定自定义的负载均衡规则
 * name:指定该规则对哪个服务生效
 */
@ComponentScan(basePackages = "xyz.yurihentai.cloud.consul", excludeFilters = {@ComponentScan.Filter(type= FilterType.REGEX, pattern = "xyz.yurihentai.cloud.consul.ribbonrule.*")})
@RibbonClient(name = "cloud-consul-provider", configuration = MyRule.class)
@EnableDiscoveryClient
@SpringBootApplication
public class ConsulComsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsulComsumerApplication.class, args);
    }

}