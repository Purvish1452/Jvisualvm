public class Deadlock {
    static final Object lock1 = new Object();
    static final Object lock2 = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (lock1) {
                try { Thread.sleep(100); } catch (Exception e) {}
                synchronized (lock2) {}
            }
        }).start();

        new Thread(() -> {
            synchronized (lock2) {
                try { Thread.sleep(100); } catch (Exception e) {}
                synchronized (lock1) {}
            }
        }).start();
    }
}