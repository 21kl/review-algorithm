package reactor;

public class Server {
    private Selector selector = new Selector();
    private Dispatcher loopDispatcher = new Dispatcher(selector);
    private Acceptor acceptor;

    public Server(int port) {
        acceptor = new Acceptor(port, selector);
    }

    public void start() {
        loopDispatcher.registerEventHandler(EventType.ACCEPT, new AcceptEventHandler(selector));
//        new Thread(acceptor,"Acceptor-"+acceptor.getPort()).start();
        loopDispatcher.dispatcher();
    }
}
