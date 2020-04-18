package reactor;

/**
 * 处理接受accept的请求
 */
public class AcceptEventHandler extends EventHandler {

    private Selector selector;

    public AcceptEventHandler(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void handler(Event event) {
        System.out.println("AcceptEventHandler:处理接受请求的事件");
        //处理事件类型为accept的事件
        if (event.getType() == EventType.ACCEPT) {
            // TODO: 2020/2/6 在这里处理状态为accept的事件

            //将事件的状态改为read，并且放入selector的阻塞队列里面
            Event readEvent = new Event();
            readEvent.setType(EventType.READ);
            readEvent.setSource(event.getSource());
            System.out.println("AcceptEventHandler:改变接受事件的状态并放入阻塞队列的缓存中");
            selector.addEvent(readEvent);
        }
    }
}
