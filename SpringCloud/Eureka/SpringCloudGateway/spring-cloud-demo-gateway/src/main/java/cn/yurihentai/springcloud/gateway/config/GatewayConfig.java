package cn.yurihentai.springcloud.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    /**
     * 通过配置类配置路由（除配置文件之外的另一种方式）
     * 配置一个id为 yuri_hentai 的路由规则，当访问 本服务的 /yurihentai 请求时 会自动转发到地址：http://www.yurihentai.cn
     * @return
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder)
    {
        RouteLocatorBuilder.Builder routes = builder.routes();

        return builder.routes().route("yuri_hentai", r -> r.path("/yurihentai").uri("http://www.yurihentai.cn"))
              .route("yuri", r -> r.path("/yuri").uri("http://www.yurihentai.cn"))
              .build();

    }

}

