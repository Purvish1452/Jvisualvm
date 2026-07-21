public class WaitingThread {
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(() -> {
            synchronized (WaitingThread.class) {
                try {
                    WaitingThread.class.wait();
                } catch (Exception e) {}
            }
        });

        t.start();
    }
}