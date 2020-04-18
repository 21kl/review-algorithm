package singleton;

/**
 * 双检锁来实现单例模式，其实就是安全版本的懒汉式单例
 * <p>
 * 如果不加入volatile可能会出现问题
 * instance = new DoubleCheckSingleton();分为三个步骤
 * 1.在堆内存开辟内存空间。
 * 2.在堆内存中实例化SingleTon里面的各个参数。
 * 3.把对象指向堆内存空间。
 * 由于jvm可能存在优化的现象导致乱序执行，比如导致3比2先执行，那么，其他线程来获取的时候就会出错
 */
public class DoubleCheckSingleton {
    private static volatile DoubleCheckSingleton instance = null;

    private DoubleCheckSingleton() {
    }

    public static DoubleCheckSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckSingleton();
                }
            }
        }
        return instance;
    }
}
