package reactor;

public abstract class EventHandler {
    private InputSource source;

    public InputSource getSource() {
        return source;
    }

    public void setSource(InputSource source) {
        this.source = source;
    }

    /*
    事件处理方法
     */
    public abstract void handler(Event event);
}
