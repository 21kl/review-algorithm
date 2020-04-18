package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 通常用于异步获取执行结果或者取消任务的场景
 */
public class FutureTaskExample {
    public static void main(String[] args) {
        FutureTask<Integer> futureTask = new FutureTask<>(new Callable<Integer>() {
            //在这里进行异步的耗时操作
            @Override
            public Integer call() throws Exception {
                int res = 0;
                for (int i = 0; i < 100; i++) {
                    Thread.sleep(50);
                    res += i;
                }
                return res;
            }
        });
        Thread thread1 = new Thread(futureTask);
        Thread thread2 = new Thread() {
            @Override
            public void run() {
                System.out.println("thread2 is rum");
            }
        };
        thread1.start();
        thread2.start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
