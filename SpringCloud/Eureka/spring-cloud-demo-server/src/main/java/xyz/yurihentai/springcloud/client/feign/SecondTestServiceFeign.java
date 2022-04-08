package xyz.yurihentai.springcloud.client.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import xyz.yurihentai.springcloud.client.feign.fallback.SecondTestServiceFallback;

@FeignClient(value = "SPRING-CLOUD-PROVIDER-SECOND", fallback = SecondTestServiceFallback.class, qualifier = "secondTestServiceFeign")
public interface SecondTestServiceFeign {

    @GetMapping("/test")
    String test();

}