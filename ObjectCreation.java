import java.util.*;

public class ObjectCreation {
    public static void main(String[] args) throws Exception {
        List<String> list = new ArrayList<>();

        while (true) {
            list.add(new String("Employee"));
            Thread.sleep(10);
        }
    }
}