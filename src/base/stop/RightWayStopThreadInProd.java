package base.stop;

public class RightWayStopThreadInProd implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("go");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                System.out.println("保存日志");
                e.printStackTrace();
            }
        }
    }

    private void throwInMethod() throws InterruptedException {
        Thread.sleep(2000);
    }
}
