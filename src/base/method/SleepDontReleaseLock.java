package base.method;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SleepDontReleaseLock implements Runnable{

    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        SleepDontReleaseLock runnable = new SleepDontReleaseLock();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }

    @Override
    public void run() {
        lock.lock();
        System.out.println("线程" + Thread.currentThread().getName() + "获取到了锁");
        try {
            Thread.sleep(5000);
            System.out.println("线程" + Thread.currentThread().getName() + "已经苏醒");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
