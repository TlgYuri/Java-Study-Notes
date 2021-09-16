package xyz.yurihentai.springcloud.client.feign.fallback;

import org.springframework.stereotype.Component;
import xyz.yurihentai.springcloud.client.feign.SecondTestServiceFeign;

@Component
public class SecondTestServiceFallback implements SecondTestServiceFeign {

    @Override
    public String test() {
        return "error Second";
    }

}