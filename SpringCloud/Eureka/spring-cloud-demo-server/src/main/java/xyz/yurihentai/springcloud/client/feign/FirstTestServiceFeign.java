package xyz.yurihentai.springcloud.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.yurihentai.springcloud.client.feign.fallback.FirstTestServiceFallback;

/**
 * *@FeignClient : 声明自己要调用注册中心的哪个服务 value=服务名称
 * fallback: 指定出现异常时用于处理异常的类，此类必须为自定义的实现了本接口的子类，用于出问题时返回自定义的默认信息
 */
@FeignClient(value = "SPRING-CLOUD-PROVIDER-FIRST", fallback = FirstTestServiceFallback.class, qualifier = "firstTestServiceFeign")
public interface FirstTestServiceFeign {

    // 与被调用端的方法保持一致
    @GetMapping("/test")
    String test();

}