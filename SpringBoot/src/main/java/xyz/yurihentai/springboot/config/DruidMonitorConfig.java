package xyz.yurihentai.springboot.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidMonitorConfig {

    //一种获取配置文件数据  手动注入bean的方式
//    @ConfigurationProperties(prefix = "spring.datasource")  //获取配置文件中以spring.datasource开头的属性
//    @Bean //将方法的返回值作为Bean对象注入容器中
//    public DataSource dataSource() throws SQLException {
//        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setFilters("stat");  //配置监控统计拦截的filters
//        return dataSource;
//    }

    //配置Druid的监控
    //1、配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet() {
        //指定监控后台所在路径
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //指定监控后台其他相关配置
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "123456");
        initParams.put("allow", "");    // 默认就是允许所有访问
        initParams.put("deny", "192.114.5.14");//拒绝哪个ip访问
        bean.setInitParameters(initParams);
        return bean;
    }

    //2、配置一个web监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");//排除过滤
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }

}