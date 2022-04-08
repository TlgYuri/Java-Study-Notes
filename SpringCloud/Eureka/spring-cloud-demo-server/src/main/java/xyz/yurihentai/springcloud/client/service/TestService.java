package xyz.yurihentai.springcloud.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.yurihentai.springcloud.client.feign.FirstTestServiceFeign;
import xyz.yurihentai.springcloud.client.feign.SecondTestServiceFeign;

@Service
public class TestService {

    private final FirstTestServiceFeign firstTestServiceFeign;
    private final SecondTestServiceFeign secondTestServiceFeign;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public TestService(FirstTestServiceFeign firstTestServiceFeign,
                       SecondTestServiceFeign secondTestServiceFeign) {
        this.firstTestServiceFeign = firstTestServiceFeign;
        this.secondTestServiceFeign = secondTestServiceFeign;
    }

    // 另一种熔断方式
    // @HystrixCommand 指定出问题的时候调用的方法，参数和返回值与此方法一致
//    @HystrixCommand(fallbackMethod = "hystrix")
    public String testFirst() {
        return firstTestServiceFeign.test();
    }

    public String testSecond() {
        return secondTestServiceFeign.test();
    }

    public String hystrix() {
        return "error";
    }

}
