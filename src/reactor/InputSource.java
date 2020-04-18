package reactor;

public class InputSource {
    private Object data;
    private int id;

    @Override
    public String toString() {
        return "InputSource{" +
                "data=" + data +
                ", id=" + id +
                '}';
    }

    public InputSource(Object data, int id) {
        this.data = data;
        this.id = id;
    }
}
