package singleton;

/**
 * 外部类第一次加载的时候并不会加载静态内部类，直到调用getInstance的时候才会去实例化instance而且智慧去加载一次
 */
public class StaticInnerSingleton {
    private StaticInnerSingleton() {
    }

    private static class StaticInnerSingletonHoler {
        private static StaticInnerSingleton instance = new StaticInnerSingleton();
    }

    public static StaticInnerSingleton getInstance() {
        return StaticInnerSingletonHoler.instance;
    }
}
