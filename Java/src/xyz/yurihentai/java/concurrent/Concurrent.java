package xyz.yurihentai.java.concurrent;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

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

    /* 内存模型
        每个线程拥有一个独立的栈内存，保存方法信息和基本数据类型，使用到的对象在栈中保存的是对象的引用，实际对象存储在堆中。
        全局变量保存在堆中。
        基本数据类型通常保存在栈中，但实例对象中定义的的基本数据类型，会与该对象一同保存在堆中。
    */

    @Test
    /** synchronized 同步 */
    public void testSynchronized() {
        // 同步时会使用一个对象作为同步的监视器，使用同一个对象同步的方法或代码块同时只能有一个线程访问执行，其他线程访问时会被阻塞
        // 使用class对象同步，区别于使用实例对象同步，一个类在jvm中只会加载一次
        // 同步方法 synchronized修饰的方法
        //      静态方法：同步对象是本类  public static synchronized String add() {}
        //      普通方法：同步对象是当前实例  public synchronized String add() {}
        // 同步代码块 可以自己指定同步的对象  synchronized(this) {}
    }

    @Test
    /** 线程通信  Object类提供的方法：wait、notify、notifyAll */
    public void testWaitNotify() {
        Object lock = new Object();
        Thread thread1 = new Thread(()->{
            synchronized (lock) {
                try {
                    System.out.println(Thread.currentThread().getName() + "等待");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + "已被唤醒");
        });
        Thread thread2 = new Thread(()->{
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + "准备唤醒");
                lock.notifyAll();
            }
        });
        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }

    @Test
    /** ThreadLocal 不同线程设置不同的值，获取时获取自己设置的值，不会覆盖其他线程的值 */
    public void testThreadLocal() {
        // 子类InheritableThreadLocal  允许子线程获取到父线程设置的值
        ThreadLocal tl = new ThreadLocal<String>();  // 不设置泛型时返回Object  需要做转型操作

        Thread tA = new Thread(()->{
            tl.set("Thread-A" + new Random().nextInt());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("tA:" + tl.get());
        });

        Thread tB = new Thread(()-> {
            tl.set("Thread-B" + new Random().nextInt());
            try {
                TimeUnit.SECONDS.sleep(5);
                tA.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("tB:" + tl.get());
        });

        tA.start();
        tB.start();
        try {
            tA.join();  // JUnitTest这里需要手动join一次  否则不会执行多线程
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    /** CAS 比较并替换  java.util.concurrent.atomic包 原子操作 */
    public void testCAS() {
        AtomicInteger ai = new AtomicInteger(1);
        // 当变量值与预期一致时，替换为目标值    方法返回boolean，标志替换操作是否成功
        ai.compareAndSet(1,2);
        System.out.println(ai);
        ai.compareAndSet(1,2);
        System.out.println(ai);

        Thread ta = null;
        for (int i = 0; i < 5 ; i++) {
            ta = new Thread(() -> {
                for (int j = 0; j <= 9; j++) {
                    boolean update = false;
                    while (!update) { // 尝试更新，如果更新失败则继续重试
                        int temp = ai.get();
                        update = ai.compareAndSet(temp, temp + 1);
                        System.out.println(Thread.currentThread().getName() + "-更新状态-" + update + "\n当前值：" + ai.get());
                    }
                }
            }, "Thread" + i);
            ta.start();
        }

        try {
            ta.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}