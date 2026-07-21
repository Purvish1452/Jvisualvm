import java.util.*;

public class HeapMemory {
    public static void main(String[] args) throws Exception {
        List<byte[]> list = new ArrayList<>();

        while (true) {
            list.add(new byte[1024 * 1024]); // 1 MB
            Thread.sleep(500);
        }
    }
}