package by.bsu;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Storage extends HttpServlet {

    public static final ArrayList<User> usersList
            = new ArrayList<User>(Arrays.asList(
            new User("guest", "10iut"),
            new User("petrov", "10ddd"),
            new User("sidorov", "12vvv"),
            new User("mishin", "12trt"),
            new User("vasin", "14qwe")
    ));




}
/**/
