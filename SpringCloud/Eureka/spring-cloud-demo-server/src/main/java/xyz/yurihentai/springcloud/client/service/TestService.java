package xyz.yurihentai.springcloud.client.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.yurihentai.springcloud.client.feign.FirstTestServiceFeign;
import xyz.yurihentai.springcloud.client.feign.SecondTestServiceFeign;

@Service
// @DefaultProperties(defaultFallback = "hystrix")  // 指定全局默认的服务降级方法（仅对标注了@HystrixCommand，且没有指定fallbackMethod的方法生效）
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

    /**
     * *@FeignClient之外的，另一种使用方式（此种写法 代码冗余且与业务代码耦合，不推荐）
     * *@HystrixCommand
     * fallback：指定feign调用异常时调用的方法，参数和返回值需要与当前方法一致
     * commandProperties：指定一些参数 如调用等待时间等
     */
    @HystrixCommand(fallbackMethod = "hystrix", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String testFirst() {
        return firstTestServiceFeign.test();
    }

    @HystrixCommand
    public String testSecond() {
        return secondTestServiceFeign.test();
    }

    public String hystrix() {
        return "error";
    }

}
