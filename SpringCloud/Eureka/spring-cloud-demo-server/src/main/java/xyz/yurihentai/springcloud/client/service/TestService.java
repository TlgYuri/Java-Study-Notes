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
     * 相关属性和默认值参考：com.netflix.hystrix.HystrixCommandProperties.class
     */
    @HystrixCommand(fallbackMethod = "hystrix", commandProperties = {
            // 1、熔断的触发条件：在调用等待时间内，接收到的请求超过请求数量，且失败率达到指定比例
            // 2、熔断器半开：在滑动时间单位后尝试处理请求，如果调用等待时间内接收到的请求失败率未达到指定比例则关闭熔断
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "15000"),    //调用等待时间（单位毫秒，默认10s），当前接口执行时间超过指定时间后会执行fallback服务降级
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),   // 启用熔断器，除了此配置还需要@EnableCircuitBreaker
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),    // 时间滑动单位（单位毫秒，默认5s）
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "25"),  // 请求数量（默认20）
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60") // 触发熔断的失败率百分比（默认50%）
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