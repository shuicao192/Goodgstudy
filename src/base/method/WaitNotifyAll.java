package base.method;

public class WaitNotifyAll implements Runnable{

    public static final Object object = new Object();

    public static void main(String[] args) {
        Runnable r = new WaitNotifyAll();
        Thread threadA = new Thread(r);
        Thread threadB = new Thread(r);
        Thread threadC = new Thread(() -> {
            synchronized (object) {
                object.notifyAll();
                System.out.println("ThreadC notified.");
            }
        });
        threadA.start();
        threadB.start();
        threadC.start();
    }

    @Override
    public void run() {
        synchronized (object) {
            System.out.println(Thread.currentThread().getName()+" got resourceA lock.");
            try {
                System.out.println(Thread.currentThread().getName()+" waits to start.");
                object.wait();
                System.out.println(Thread.currentThread().getName()+"'s waiting to end.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
