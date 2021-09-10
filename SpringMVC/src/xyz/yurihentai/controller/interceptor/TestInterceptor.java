package xyz.yurihentai.controller.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//需要在springmvc.xml中配置
//两种实现方式  实现HandlerInterceptor接口  或者  继承HandlerInterceptorAdapter抽象类
public class TestInterceptor implements HandlerInterceptor {

    @Override  //处理请求前执行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        System.out.println(this.getClass().getName() + " - preHandle");
        if (true) {
            return true;
        }
        return false;  // 返回false代表拒绝请求
    }

    @Override  //处理请求完成，返回给客户端之前执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println(this.getClass().getName() + " - postHandle");
    }

    @Override  //最后执行  处理请求完成，返回给客户端之后执行
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
        System.out.println(this.getClass().getName() + "- afterCompletion");
    }
}

//public class TestInterceptor extends HandlerInterceptorAdapter {
//    public TestInterceptor() {
//        super();
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        return super.preHandle(request, response, handler);
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        super.postHandle(request, response, handler, modelAndView);
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        super.afterCompletion(request, response, handler, ex);
//    }
//
//    @Override
//    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        super.afterConcurrentHandlingStarted(request, response, handler);
//    }
//}