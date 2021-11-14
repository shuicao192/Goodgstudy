package base.deadlock;

public class TransforMoney implements Runnable {

    int flag = 1;

    static Acount a = new Acount(500);
    static Acount b = new Acount(500);

    public static void main(String[] args) throws InterruptedException {
        TransforMoney r1 = new TransforMoney();
        TransforMoney r2 = new TransforMoney();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("转账完成");
    }

    @Override
    public void run() {
        if (flag == 1) {
            transforMoney(a, b, 200);
        } else {
            transforMoney(b, a, 200);
        }
    }

    private void transforMoney(Acount from, Acount to, int acount) {
        synchronized (from) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (to) {
                if (a.balance < acount) {
                    System.out.println("余额不足，转账失败");
                    return;
                }
                a.balance -= acount;
                b.balance += acount;
            }
        }
    }
}

class Acount {
    int balance;

    public Acount(int balance) {
        this.balance = balance;
    }
}
