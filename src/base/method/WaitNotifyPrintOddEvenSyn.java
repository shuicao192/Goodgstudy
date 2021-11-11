package base.method;

public class WaitNotifyPrintOddEvenSyn {

    public static final Object object = new Object();
    public static int count;

    public static void main(String[] args) {
        new Thread(() -> {
            while (count < 100) {
                synchronized (object) {
                    if ((count & 1) == 0) {
                        System.out.println(Thread.currentThread().getName() + ":" + count++);
                    }
                }
            }
        },"偶数").start();
        new Thread(() -> {
            while (count < 100) {
                synchronized (object) {
                    if ((count & 1) == 1) {
                        System.out.println(Thread.currentThread().getName() + ":" + count++);
                    }
                }
            }
        },"奇数").start();
    }
}
