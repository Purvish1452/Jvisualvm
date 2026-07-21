import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String a[])
    {
        System.out.println("Application Started");

        List<User> users=new ArrayList<>();

        for(int i=0;i<=10000;i++)
        {
            users.add(new User());
        }
        System.out.println("Application Ended");
    }

}