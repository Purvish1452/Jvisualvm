import java.util.*;

public class MemoryLeak {

    static List<Object> list = new ArrayList<>();

    public static void main(String[] args) {

        while (true) {
            list.add(new byte[1024 * 1024]); // 1 MB
        }
    }
}