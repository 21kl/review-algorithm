package reactor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Acceptor implements Runnable {

    private int port;
    private Selector selector;
    //模拟外部请求队列
    private BlockingQueue<InputSource> sourcesBlockingQueue = new LinkedBlockingQueue<>();

    public Acceptor(int port, Selector selector) {
        this.port = port;
        this.selector = selector;
    }

    /**
     * 请求到来之后加入到阻塞队列
     *
     * @param source
     */
    public void addNewConnect(InputSource source) {
        sourcesBlockingQueue.offer(source);
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public void run() {

        while (true) {
            InputSource source = null;
            //从请求队列中获取请求
            try {
                source = sourcesBlockingQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //将事件添加到选择器
            if (null != source) {
                Event acceptEvent = new Event();
                acceptEvent.setSource(source);
                acceptEvent.setType(EventType.ACCEPT);
                selector.addEvent(acceptEvent);
            }
        }
    }
}
