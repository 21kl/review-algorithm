package thread;

/**
 * 一个展示同步synchronized的类
 */
public class SynchronizedExample {
    /**
     * 同步代码块
     */
    public void func1() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        }
    }

    /**
     * 同步方法
     */
    public synchronized void func2() {
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");
        }
    }

    /**
     * 同步类
     */
    public void func3() {
        synchronized (SynchronizedExample.class) {
            for (int i = 0; i < 5; i++) {
                System.out.print(i + " ");
            }
        }
    }

    /**
     * 同步静态方法
     */
    public static synchronized void func4() {
        for (int i = 0; i < 5; i++) {
            System.out.print(i + " ");
        }
    }
}
