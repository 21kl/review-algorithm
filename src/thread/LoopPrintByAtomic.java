package thread;

import sun.management.snmp.jvminstr.JvmThreadInstanceEntryImpl;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 通过原子类来实现多线程循环打印ABC
 */
public class LoopPrintByAtomic {
    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(atomicInteger.get()%3==0){
                        System.out.print("A");
                        atomicInteger.getAndIncrement();
                    }
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(atomicInteger.get()%3==1){
                        System.out.print("B");
                        atomicInteger.getAndIncrement();
                    }
                }

            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if(atomicInteger.get()%3==2){
                        System.out.println("C");
                        atomicInteger.getAndIncrement();
                    }
                }

            }
        });
        t3.start();
        t2.start();
        t1.start();
    }
}
