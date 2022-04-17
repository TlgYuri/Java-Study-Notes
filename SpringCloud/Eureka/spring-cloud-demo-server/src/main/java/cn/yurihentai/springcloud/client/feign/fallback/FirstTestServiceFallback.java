package cn.yurihentai.springcloud.client.feign.fallback;

import org.springframework.stereotype.Component;
import cn.yurihentai.springcloud.client.feign.FirstTestServiceFeign;

@Component
public class FirstTestServiceFallback implements FirstTestServiceFeign {

    //Feign调用出现问题时会调用此方法替代
    @Override
    public String test() {
        return "error First";
    }

}