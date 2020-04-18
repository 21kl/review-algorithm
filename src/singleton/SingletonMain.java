package singleton;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingletonMain {

    public static void main(String[] args) {
        int number = 10;
        //测试懒汉式单例模式
        for (int i = 0; i < number; i++) {
            new Thread() {
                @Override
                public void run() {
                    System.out.println(LazySingleton.getInstace());
                }
            }.start();
        }
//        for (int i = 0; i < number; i++) {
//            new Thread(){
//                @Override
//                public void run() {
//                    System.out.println(HangerSingleton.getInstance());
//                }
//            }.start();
//        }
    }
}
