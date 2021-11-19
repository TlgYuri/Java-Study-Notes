package xyz.yurihentai.java.concurrent;

import org.junit.Test;

/**
 * description
 * 学习自：http://ifeve.com/java-concurrency-thread-directory/
 * @author Yuri
 * @date 2021/11/16 11:45:14
 */
public class Concurrent {

    @Test
    /** Thread 启动线程 */
    public void testThread() {
        // 实现线程操作的方式有多种，但最终都是通过Thread的start()方法启动线程
        // 虽然最终执行的是run()方法内的操作，但是区别在于直接执行run()方法实在本线程内执行，而start()方法是启动一个新的线程执行操作
        // 1、创建类继承Thread类，实现Thread的run方法（这里采用匿名对象的方式
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println("Hello Thread!");
            }
        };
        thread.start();
        // 2、创建类实现Runnable接口，将该类的实例作为Thread类的构造参数（Runnable是个函数式接口，这里采用匿名对象方式，用lambda表达式简写
        // 第二个位置参数可以指定线程的名称
        Thread thread2 = new Thread(()->{
            System.out.println("Hello Runnable!");
            // 获取当前执行的线程，然后获取线程名称
            System.out.println(Thread.currentThread().getName());
        }, "TestRunnable");
        thread2.start();
    }

}