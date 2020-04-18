package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadMain {
    public static void main(String[] args) {
        ImplementsRunnable implementsRunnable = new ImplementsRunnable();
        Thread t1 = new Thread(implementsRunnable, "一号");
        Thread t2 = new Thread(implementsRunnable, "二号");
        Thread t3 = new Thread(implementsRunnable, "三号");
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            executorService.execute(new ImplementsRunnable());
        }
        executorService.shutdown();
    }
}
