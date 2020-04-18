package thread;

public class ExtendsThread extends Thread {
    private int ticket = 10;
    private String name;

    public ExtendsThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            if (ticket > 0) {
                System.out.println(this.name + " 售票------>" + this.ticket--);
            }
        }
    }
}
