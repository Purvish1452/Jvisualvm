public class ManyThreads {
    public static void main(String[] args) {
        while (true) {
            new Thread(() -> {
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {}
            }).start();
        }
    }
}