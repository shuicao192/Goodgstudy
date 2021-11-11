package base.method;

public class JoinInterrupt {
    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        Thread thread = new Thread(() -> {
            mainThread.interrupt();
            try {
                Thread.sleep(5000);
                System.out.println("Thread1 finished.");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("子线程中断");
            }
        });
        thread.start();
        System.out.println("等待子线程运行完毕");
        try {
            thread.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"线程中断了");
            thread.interrupt();
        }
        System.out.println("子线程已运行完毕");
    }
}
