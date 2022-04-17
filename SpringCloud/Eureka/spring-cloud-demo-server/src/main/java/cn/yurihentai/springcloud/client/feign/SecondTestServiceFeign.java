package cn.yurihentai.springcloud.client.feign;

import cn.yurihentai.springcloud.client.feign.fallback.SecondTestServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "SPRING-CLOUD-PROVIDER-SECOND", fallback = SecondTestServiceFallback.class, qualifier = "secondTestServiceFeign")
public interface SecondTestServiceFeign {

    @GetMapping("/test")
    String test();

}