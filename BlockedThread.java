public class BlockedThread {
    static final Object lock = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (lock) {
                while (true) {}
            }
        }).start();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("Got lock");
            }
        }).start();
    }
}