public class TemporaryObjects {

    public static void main(String[] args) {

        while (true) {
            for (int i = 0; i < 100000; i++) {
                String s = new String("Hello");
            }
        }
    }
}