package cn.yurihentai.springcloud.stream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 用的依赖是新版(3.x)  但是用法采用的老版本(3.x之前)
 *
 * 如果要用新版  用法不一样  这里暂时保留  以后补充
 *
 * 如果要这么用 依赖要换成老版本(3.x之前)  否则会有一些错误  比如consumer启用分组后 结束应用的时候会报错：
 * org.springframework.beans.factory.BeanCreationNotAllowedException: Error creating bean with name 'testExchange.groupA.errors.bridge': Singleton bean creation not allowed while singletons of this factory are in destruction (Do not request a bean from a BeanFactory in a destroy method implementation!)
 */
@SpringBootApplication
public class StreamProviderApplication {

    public static void main(String args[]) {
        SpringApplication.run(StreamProviderApplication.class,args);
    }

}