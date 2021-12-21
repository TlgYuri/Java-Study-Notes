package xyz.yurihentai.java.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Yuri
 * @date 2021/12/21 11:45:14
 */
public class ThreadData {

    private int statusCode;
    private String data;

    //定义锁
    private Lock lock = new ReentrantLock();
    //定义钥匙
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();

    public ThreadData() {
        this.statusCode = ThreadStatus.NULL_STATUS.getCode();
        this.data = null;
    }

    public void setDataNull() {
        //上锁
        lock.lock();
        try {
            while (statusCode != ThreadStatus.EMPTY_STATUS.getCode()) {
                try {
                    //使用钥匙  进行等待
                    condition1.await();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + "报错");
                    e.printStackTrace();
                }
            }
            this.data = null;
            System.out.println("当前线程：" + Thread.currentThread().getName() + "，执行结果:" + this.data);
            this.statusCode = ThreadStatus.NULL_STATUS.getCode();
        } finally {
            //根据使用的钥匙决定唤醒哪个线程
            condition2.signal();
            //释放锁
            lock.unlock();
        }
    }

    public void setDataEmpty() {
        lock.lock();
        try {
            while (statusCode != ThreadStatus.NULL_STATUS.getCode()) {
                try {
                    condition2.await();
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + "报错");
                    e.printStackTrace();
                }
            }
            this.data = "";
            System.out.println("当前线程：" + Thread.currentThread().getName() + "，执行结果:" + this.data);
            this.statusCode = ThreadStatus.EMPTY_STATUS.getCode();
        } finally {
            condition1.signal();
            lock.unlock();
        }
    }

}