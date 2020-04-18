package singleton;

/**
 * 饿汉式单例模式，会在最开始就进行实例化，是线程安全的
 */
public class HangerSingleton {
    private static HangerSingleton instance = new HangerSingleton();

    private HangerSingleton() {
    }

    public static HangerSingleton getInstance() {
        return instance;
    }
}
