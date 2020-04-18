package thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier的模拟，需要等到所有的线程都await了之后才能继续执行之后的操作
 */
public class CyclicBarraiesExample {
    public static void main(String[] args) {
        int total = 10;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(total);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < total; i++) {
            executorService.execute(() -> {
                System.out.print("before..");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.print("after..");
            });
        }
        executorService.shutdown();
    }
}
