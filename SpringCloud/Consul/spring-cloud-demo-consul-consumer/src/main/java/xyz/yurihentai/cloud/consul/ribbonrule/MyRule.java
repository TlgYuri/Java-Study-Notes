package xyz.yurihentai.cloud.consul.ribbonrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description： 指定负载均衡规则(也可在yml配置文件中配置)
 * @author： Yuri
 * @date： 2022/4/8 11:45:14
 */
@Configuration
public class MyRule {

    /**
     * IRule是Ribbon所有负载均衡规则的父接口
     * com.netflix.loadbalancer.RoundRobinRule：轮询（默认规则）
     * com.netflix.loadbalancer.RandomRule：随机
     * com.netflix.loadbalancer.RetryRule：在指定时间内如果获取服务失败或获取到的服务不可用，则继续获取
     * com.netflix.loadbalancer.WeightedResponseTimeRule：对轮询的扩展，响应速度越快的实例选择权重越大（加权轮询）
     * com.netflix.loadbalancer.AvailabilityFilteringRule：过滤掉短路和并发量高的实例，然后轮询
     * com.netflix.loadbalancer.BestAvailableRule：忽略短路的服务，选择连接数最小的服务（最小连接）
     * com.netflix.loadbalancer.ZoneAvoidanceRule：使用Zone对服务器进行分类，对Zone内的多个服务做轮询
     * @return
     * 许多规则继承了轮询，如果当前规则高级策略无法满足，则会调用父类的轮询规则
     */
    @Bean
    public IRule myRule() {
        return new RandomRule();
    }

}