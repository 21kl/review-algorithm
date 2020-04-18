package thread;

public class ImplementsRunnable implements Runnable {
    private int ticket = 10;

    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            if (this.ticket > 0) {
                System.out.println(Thread.currentThread().getName() + " ---->" + (this.ticket--));
            }
        }
    }
}
