package javaa;

import java.util.ArrayList;
import java.util.Arrays;

public class Storage {
    public static final ArrayList<User> users
            = new ArrayList<User>(Arrays.asList(
            new User("ivanov", "10iut"),
            new User("petrov", "10ddd"),
            new User("sidorov", "12vvv"),
            new User("mishin", "12trt"),
            new User("vasin", "14qwe")
    ));

}
