package xyz.yurihentai.springaop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@Order(1)
public class MyLogger {
    //执行顺序：around、before、after、afterReturning 或 afterThrowing
    //around 环绕通知，类似于动态代理，包含所有通知

    //定义切入点，在设置切入点时直接使用本方法名即可获取本方法设置的切入点的值
    @Pointcut("execution(* xyz.yurihentai.springaop.*.*(..))")  //任意访问修饰符和返回值  org.yuri.springaop包下的所有包下的所有类的所有方法 (..)表示无关任意参数个数，匹配所有方法
    public void pointcut() {
    }

    @Before("execution(* xyz.yurihentai.springaop.*.*(..))")
    public void doBefore(JoinPoint jp) {
        System.out.println(("[before] method name = " + jp.getSignature().getName() + ", args:" + Arrays.toString(jp.getArgs())));
    }

    @AfterReturning(value = "pointcut()", returning = "result")
    public void doReturn(JoinPoint jp, Object result) {
        System.out.println("[return] method " + jp.getSignature().getName() + ", result:" + result);
    }

    @After("pointcut()")
    public void doAfter() {
        System.out.println("[finally] method done"); //finally在return之前执行
    }

    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void doThrowing(JoinPoint jp, Exception e) {
        System.out.println("throwing exception..." + e.getStackTrace());
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) {
        try {
            System.out.println("前置通知");
            Object result = pjp.proceed();
            System.out.println("返回通知\n" + result); //return语句实际运行时在finally后执行
            return result;
        } catch (Throwable e) {
            System.out.println("异常通知");
            e.printStackTrace();
        } finally {
            System.out.println("后置通知");
        }
        return null;
    }

}
