package cn.yurihentai.springboot.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class TestListener implements ServletContextListener { //SpringApplicationRunListener 监控SpringBoot整个生命周期

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("ServletContext创建完毕");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("ServletContext已销毁");
    }

}