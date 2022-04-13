package xyz.yurihentai.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// @Import 生成指定类的对象并注入进容器中 主要用于自动配置类中引入 配置类
// @Import({DispatcherServletAutoConfiguration.class})
// @ImportResource 引入xml配置文件（旧的SSM项目升级为SpringBoot 此时如果有xml文件可以直接导入，不用重新配置）
// @ImportResource({"classpath:beans.xml"})
// 扫描Mapper接口
@MapperScan("xyz.yurihentai.springboot.mapper")
// 扫描组件 默认扫描启动类同一个包下的组件 此项用于组件与启动类不在同一个包下的情况指定需要扫描的组件
@ComponentScan("xyz.yurihentai")
// 扫描servlet组件  配置此项后可通过@WebServlet @WebFilter @WebListener注解直接引入servlet、filter、listener
@ServletComponentScan({"xyz.yurihentai.springboot.listener","xyz.yurihentai.springboot.filter"})
// 启用事务管理器  在需要使用事务的方法上使用@Transactional即可
@EnableTransactionManagement
/**
 * 自动配置原理：
 * *@SpringBootApplication 内有 @EnableAutoConfiguration
 *     1、@EnableAutoConfiguration 上有 @Import(AutoConfigurationImportSelector.class)和 @AutoConfigurationPackage
 *         1.1、通过 AutoConfigurationImportSelector 中的 getAutoConfigurationEntry 方法 导入各start提供的自动配置类
 *           → 步骤：调用 getCandidateConfigurations 方法中调用的 SpringFactoriesLoader.loadFactoryNames 方法 调用的 loadSpringFactories 方法
 *                 通过 类加载器的 getResource 方法 获取 静态常量指定位置下的文件 FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";
 *                 在该文件中指定了所有要加载的自动配置类  通过自动配置类引入指定的 整个环境所需的 组件
 *         1.2、@AutoConfigurationPackage 上通过 @Import(AutoConfigurationPackages.Registrar.class) 引入了自动配置的Register
 *     2、按需配置 简单举例 @Condition等
 *       * @ConditionalOnMissingBean 容器中没有指定对象时生效、@ConditionalOnMissingClass 项目中引入了指定类的依赖时才生效、@ConditionalOnMissingClass 项目中没有引入指定类的依赖时生效
 *       * @AutoConfigureAfter 在指定的自动配置类之后开始配置
 */
@SpringBootApplication
public class MySpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringBootApplication.class, args);
    }

}
