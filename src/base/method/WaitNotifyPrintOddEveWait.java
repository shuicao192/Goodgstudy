package base.method;

public class WaitNotifyPrintOddEveWait {
    public static int count;
    public static final Object object = new Object();

    public static void main(String[] args) {
        new Thread(new TurningRunner(), "偶数").start();
        new Thread(new TurningRunner(), "奇数").start();
    }

    static class TurningRunner implements Runnable {

        @Override
        public void run() {
            while (count <= 100) {
                synchronized (object) {
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    object.notify();
                    if (count <= 100) {
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }}
