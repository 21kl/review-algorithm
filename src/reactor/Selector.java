package reactor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 核心的select方法从缓冲队列中查找符合条件的event
 */
public class Selector {

    private BlockingQueue<Event> eventBlockingQueue = new LinkedBlockingQueue<>();
    private Object lock = new Object();

    public List<Event> select() {
        return select(0);
    }

    /**
     * 选择符合条件的事件
     *
     * @param timeout
     * @return
     */
    public List<Event> select(long timeout) {
        //如果存在最大的超时时间就需要另外处理,使用双检锁来实现
        if (timeout > 0) {
            if (eventBlockingQueue.isEmpty()) {
                synchronized (lock) {
                    if (eventBlockingQueue.isEmpty()) {
                        try {
                            lock.wait(timeout);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } finally {
                            System.out.println("timeout");
                        }
                    }
                }
            }
        }
        List<Event> events = new ArrayList<>();
        // TODO: 2020/2/6 这里其实可以将符合业务条件的事件放入到对应的阻塞队列中去
        eventBlockingQueue.drainTo(events);
        return events;
    }

    /**
     * 添加事件
     *
     * @param e
     */
    public void addEvent(Event e) {
        boolean success = eventBlockingQueue.offer(e);
        System.out.println("Selcetor:添加事件进入队列");
        //如果有新增事件则对lock对象解锁
        if (success) {
            synchronized (lock) {
                lock.notify();
            }
        }
    }
}
