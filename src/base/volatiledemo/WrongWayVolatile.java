package base.volatiledemo;

public class WrongWayVolatile implements Runnable{
    public static volatile boolean canceled = false;

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile r = new WrongWayVolatile();
        Thread thread = new Thread(r);
        thread.start();
        Thread.sleep(5000);
        canceled = true;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num < 1000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数。");
                }
                num++;
                Thread.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
