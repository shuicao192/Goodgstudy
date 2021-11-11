package base.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class WrongWayVolatileFixed {
    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatileFixed fixed = new WrongWayVolatileFixed();
        ArrayBlockingQueue<Integer> storage = new ArrayBlockingQueue<>(10);

        Producer producer = fixed.new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);

        Consumer consumer = fixed.new Consumer(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take()+"被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了。");

        producerThread.interrupt();
    }

    class Producer implements Runnable {

        BlockingQueue<Integer> storage;
        public Producer(BlockingQueue<Integer> storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            try {
                int num = 0;
                while (num <= 10000 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        storage.put(num);
                        System.out.println(num + "是100的倍数,被放到仓库中了。");
                    }
                    num++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("生产者结束运行");
            }
        }
    }

    class Consumer {

        BlockingQueue<Integer> storage;

        public Consumer(BlockingQueue<Integer> storage) {
            this.storage = storage;
        }

        public boolean needMoreNums() {
            return !(Math.random() > 0.95);
        }
    }
}


