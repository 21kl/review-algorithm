package thread;

import jdk.nashorn.internal.ir.Block;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用Blocking Queue来实现生产者和消费者的问题
 */
public class BlockingQueueExample {
    private static BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(5);

    private static class Producer extends Thread {
        @Override
        public void run() {
            try {
                blockingQueue.put("produce");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("produce..");
        }
    }

    private static class Comsumer extends Thread {
        @Override
        public void run() {
            try {
                String produce = blockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print("comsumer..");
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            Producer producer = new Producer();
            producer.start();
        }
        for (int i = 0; i < 5; i++) {
            Comsumer comsumer = new Comsumer();
            comsumer.start();
        }
        for (int i = 0; i < 2; i++) {
            Producer producer = new Producer();
            producer.start();
        }
    }
}
