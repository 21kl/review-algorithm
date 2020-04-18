package reactor;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 事件分发器的类
 */
public class Dispatcher {
    Map<EventType, EventHandler> map = new ConcurrentHashMap<>();
    private Selector selector;

    public Dispatcher(Selector selector) {
        this.selector = selector;
    }


    /**
     * 在dispatcher中注册事件
     *
     * @param eventType
     * @param eventHandler
     */
    public void registerEventHandler(EventType eventType, EventHandler eventHandler) {
        map.put(eventType, eventHandler);
    }

    /**
     * 移除事件处理器
     *
     * @param eventType
     */
    public void removeEventHandler(EventType eventType) {
        map.remove(eventType);
    }

    public void dispatcher() {
        while (true) {
            List<Event> events = selector.select();

            //不同的事件交给不同的事件处理器来进行处理
            for (Event event : events) {
                EventHandler eventHandler = map.get(event.getType());
                eventHandler.handler(event);
            }
        }
    }
}
