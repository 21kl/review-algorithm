package thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用lock和Condition来实现循环打印三个线程
 */
public class LoopPrint {
    private int number = 1;
    private ReentrantLock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void loopA(){
        lock.lock();
        try {
            //如果不是这个线程就需要进入等待
            if(number!=1){
                condition1.await();
            }
            //打印
            System.out.print(Thread.currentThread().getName());
            Thread.sleep(100);
            //唤醒第二个线程
            number = 2;
            condition2.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopB(){
        lock.lock();
        try {
            if(number!=2){
                condition2.await();
            }
            System.out.print(Thread.currentThread().getName());
            Thread.sleep(100);
            number = 3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void loopC(){
        lock.lock();;
        try {
            if(number!=3){
                condition3.await();
            }
            System.out.print(Thread.currentThread().getName());
            Thread.sleep(100);
            number = 1;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        LoopPrint loopPrint = new LoopPrint();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    loopPrint.loopA();
                }
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    loopPrint.loopB();
                }
            }
        },"B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    loopPrint.loopC();
                }
            }
        },"C").start();
    }
}
