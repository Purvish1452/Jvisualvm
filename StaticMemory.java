public class StaticMemory {

    static byte[] data = new byte[100 * 1024 * 1024];

    public static void main(String[] args) throws Exception {
        Thread.sleep(600000);
    }
}