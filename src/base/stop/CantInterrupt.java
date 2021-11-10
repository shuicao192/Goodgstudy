package base.stop;

public class CantInterrupt {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            int num = 0;
            while (num <= 1500 && !Thread.currentThread().isInterrupted()) {
                if (num % 100 == 0){
                    System.out.println(num + "是100的倍数");
                }
                num ++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
