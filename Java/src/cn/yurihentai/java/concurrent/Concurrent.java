package cn.yurihentai.java.concurrent;

import cn.yurihentai.java.concurrent.lock.MyCache;
import org.junit.Test;
import cn.yurihentai.java.concurrent.lock.ThreadData;

import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * description
 * 学习自：http://ifeve.com/java-concurrency-thread-directory/
 * @author Yuri
 * @date 2021/11/16 11:45:14
 */
public class Concurrent {

    // 线程从java最早期的版本开始就有，但juc是从jdk1.5才开始提供的

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
            try {
                // 让当前线程等待指定时间，单位毫秒
                Thread.sleep(2000);
                // jdk1.5 开始让当前线程等待的另一种方式，TimeUnit中的时间单位有多种可选
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 获取当前执行的线程，然后获取线程名称
            System.out.println(Thread.currentThread().getName());
        }, "TestRunnable");
        thread2.start();
        // 设置线程的优先级  最大：Thread.MAX_PRIORITY;  普通（默认）：Thread.MIN_PRIORITY;  最小：Thread.NORM_PRIORITY;
        thread2.setPriority(Thread.MAX_PRIORITY);
        // thread.stop(); // 方法用于停止线程，但此方法的操作并不安全，在停止线程的同时会释放线程所拥有的锁，此时可能会有未妥善处理的同步的操作
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
        // suspend（挂起）和resume（恢复）方法效果类似，但是不安全，与stop方法一样被废除不推荐使用
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
        // 不设置泛型时返回Object类型  需要做转型操作
        ThreadLocal tl = new ThreadLocal<String>();

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
    /** CAS 比较并替换  java.util.concurrent.atomic包 原子变量 */
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
//                        ai.incrementAndGet(); // 自增然后获取
//                        ai.getAndIncrement(); // 获取然后自减
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

    @Test
    /** interrupt 中断 */
    public void testInterrupt() {
        // 判断当前线程是否接收到中断信号，调用方法后清除中断状态
        boolean interrupted = Thread.interrupted();
        Thread thread = new Thread();
        // 判断指定线程是否接收到中断信号，不改变中断状态
        boolean interrupted1 = thread.isInterrupted();
        // 发送中断信号
        thread.interrupt();
        try {
            // 在执行时如果线程被中断会抛出异常
            Thread.sleep(5);
            thread.join(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }
    }

    @Test
    /** Lock 锁 */
    public void testLock() {
        ThreadData data = new ThreadData();
        Thread ta = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.setDataEmpty();
            }
        }, "置空");
        Thread tb = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                data.setDataNull();
            }
        }, "置Null");

        ta.start();
        tb.start();

        try {
            ta.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    /** ReadWriteLock 读写锁 */
    public void testReadWriteLock() {

        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        MyCache cache = new MyCache();

        Thread thread = null;
        for (int i = 0; i < 3 ; i++) {
            final int a = i;
            new Thread(()->{
                //写锁（独占锁，没有任何其他锁时才能获取到）
                lock.writeLock().lock();
                try {
                    System.out.println("线程【" + Thread.currentThread().getName() + "】准备写入-");
                    cache.insert(a+"",a);
                    System.out.println("线程【" + Thread.currentThread().getName() + "】写入完成---");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //释放锁
                    lock.writeLock().unlock();
                }
            }, "Thread-" + i).start();

            thread = new Thread(()->{
                //读锁（共享锁，同时可以有多个读锁共存）
                lock.readLock().lock();
                try {
                    System.out.println("线程【" + Thread.currentThread().getName() + "】准备读取+");
                    TimeUnit.MILLISECONDS.sleep(200);
                    System.out.println("线程【" + Thread.currentThread().getName() + "】读取完成，值为:【" +cache.select(a+"") + "】+++");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //释放锁
                    lock.readLock().unlock();
                }
            }, "Thread-" + (i+3));
            thread.start();
        }

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    /** Executor 执行器（线程池是最常见的一种执行器的实现） */
    public void testExecutor() {
        // Executor接口 通过execute(Runnable)启动线程
        // ExecutorService接口 继承了 Executor接口  扩展新增了submit(Runnable/Callable) 等操作
        // ScheduledExecutorService 继承了 ExecutorService接口  扩展新增了延时执行schedule()，按指定时间间隔定期执行任务的方法scheduleAtFixedRate()、scheduleWithFixedDelay()

        /* Executors类提供一些java进行默认参数设置的线程池的获取方法，但存在多种问题，因此一般使用自定义的线程池实现 new ThreadPoolExecutor()
                创建一个线程数量固定的线程池:  Executors.newFixedThreadPool(int n);
                    在初始化时指定手动线程的数量，等待队列长度默认为(Integer.MAX_VALUE），队列长度过大，容易导致OOM异常
                创建一个容量可扩展的线程池:    Executors.newCachedThreadPool();
                    当线程不够用时会扩展新的线程，最大线程数为Integer.MAX_VALUE，容易导致OOM异常
                创建一个单个线程的线程池:     Executors.newSingleThreadExecutor();
                    始终只有一个线程，当等待队列过长时(最多Integer.MAX_VALUE）容易导致OOM异常
                ……
         */
        ExecutorService executorService = Executors.newCachedThreadPool();
        for(int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName());
            });
        }
    }

    @Test
    /** ThreadLocalRandom 并发随机数 */
    public void testRandom() {
        // 对于并发访问，使用TheadLocalRandom代替Math.random()可以减少竞争，从而获得更好的性能。
        ThreadLocalRandom current = ThreadLocalRandom.current();
        for (int i = 0; i < 10 ; i++) {
            System.out.println(current.nextInt(69,96));
        }
    }

    @Test
    /** Callable、FutureTask */
    public void TestCallable() throws Exception {
        // 与Runnable类似，都是异步任务   区别在于callable中的方法名为call，且有返回值
        Callable<Integer> callable = new Callable() {
            @Override
            public Integer call() throws Exception {
                return 114514;
            }
        };
        // FutureTask、Thread都直接或间接的实现了Runnble接口
        FutureTask ft = new FutureTask(callable);
        Thread thread = new Thread(ft);
        thread.start();
        //判断任务是否执行完成
        ft.isDone();
        // 如果任务未开始，则取消
        // 如果任务已开始，且参数为true，则将任务中断
//        ft.cancel(true);
        // 获取计算结果，计算未完成时阻塞  如果被中断则抛出InterruptedException，如果被取消则抛出CancellationException
        Object o = ft.get();
        // 获取计算结果，超时后抛出TimeOutException
//        Object o1 = ft.get(5L, TimeUnit.SECONDS);
        System.out.println(o);
    }

}