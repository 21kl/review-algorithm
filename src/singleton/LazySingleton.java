package singleton;

/**
 * 懒汉模式，就是等到需要的时候采取初始化
 * 在多线程的情况下是不安全的
 */
public class LazySingleton {
    private static LazySingleton instace = null;

    private LazySingleton() {
    }

    public static LazySingleton getInstace() {
        if (instace == null) {
            instace = new LazySingleton();
        }
        return instace;
    }
}
