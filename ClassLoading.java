public class ClassLoading {
    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 20; i++) {
            Class.forName("java.util.ArrayList");
            Class.forName("java.util.HashMap");
            Class.forName("java.lang.String");

            Thread.sleep(1000);
        }
    }
}
//Loaded class count