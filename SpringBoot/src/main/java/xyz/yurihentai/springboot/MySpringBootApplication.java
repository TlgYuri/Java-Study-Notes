package xyz.yurihentai.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 扫描Mapper接口
@MapperScan("xyz.yurihentai.springboot.mapper")
// 扫描组件 默认扫描启动类同一个包下的组件 此项用于组件与启动类不在同一个包下的情况指定需要扫描的组件
@ComponentScan("xyz.yurihentai")
// 扫描servlet组件  配置此项后可通过@WebServlet @WebFilter @WebListener注解直接引入servlet、filter、listener
@ServletComponentScan({"xyz.yurihentai.springboot.listener","xyz.yurihentai.springboot.filter"})
// 启用事务管理器  在需要使用事务的方法上使用@Transactional即可
@EnableTransactionManagement
@SpringBootApplication
public class MySpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }

}
