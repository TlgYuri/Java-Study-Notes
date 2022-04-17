package cn.yurihentai.springcloud.client;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 启用feign（同时启动多个相同的被调用的服务 并注册到注册中心 即可自动实现负载均衡）
@EnableFeignClients
// 启用Hystrix监控仪表盘,监控启用了熔断功能的springCloud提供者服务  仪表盘的访问路径在本项目根路径下的/hystrix  要监控服务，在输入框输入路径：http://要监控的项目地址/actuator/hystrix.stream（被监控的服务需要引入actuator依赖）
@EnableHystrixDashboard
//@EnableCircuitBreaker // 启用hystrix熔断器
//@EnableDiscoveryClient    // 将本服务注册到注册中心 （@EnableEurekaClient与此作用相同，但只用于Eureka作为注册中心，原理在于同样使用了@EnableDiscoveryClient，已被取代）
//@SpringBootApplication
@SpringCloudApplication  // 这个注解相当于上面三个
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}