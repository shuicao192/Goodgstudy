package base.method;

public class Wait {

    private static final Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() ->{
            synchronized (object) {
                System.out.println(Thread.currentThread().getName() + "开始执行了");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() + "获取到了锁。");
            }
        });
        Thread thread2 = new Thread(() -> {
            synchronized (object) {
                object.notify();
                System.out.println("线程" + Thread.currentThread().getName() + "调用了notify()");
            }
        });
        thread1.start();
        Thread.sleep(200);
        thread2.start();
    }

}
