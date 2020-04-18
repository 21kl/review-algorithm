package thread;

/**
 * 使用wait和notify来交替打印奇数和偶数
 */
public class WaitAndNotify {
    private static Object obj = new Object();
    private static int i = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            while (true) {
                System.out.println("进入奇数线程");
                synchronized (obj) {
                    if (i % 2 == 1) {
                        //如果是奇数那么我们就打印奇数并且唤醒偶数的线程，再让自己进入阻塞
                        System.out.println("打印奇数：" + i++);
                        try {
                            obj.notify();
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        //如果不是奇数就进入沉睡
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }).start();

        new Thread(() -> {
            while (true) {
                System.out.println("进入偶数线程");
                synchronized (obj) {
                    //如果是偶数就先打印偶数，然后唤醒奇数线程然后让自己的线程进入沉睡
                    if (i % 2 == 0) {
                        System.out.println("打印偶数：" + i++);
                        try {
                            obj.notify();
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            obj.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }
}
