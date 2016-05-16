package javaa;

import com.google.gson.Gson;

import javax.servlet.http.HttpServlet;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Storage extends HttpServlet {
    ArrayList<User> usersList;

    Storage() throws IOException {
        File f = new File(getUsersPath());
        FileReader reader = new FileReader(f);
        char[] buffer = new char[(int) f.length()];
        reader.read(buffer);
        String s = new String(buffer);
        System.out.print(s);
        ArrayList<User> generic = new ArrayList<User>() {
        };
        usersList=new Gson().fromJson(s, generic.getClass().getGenericSuperclass());
    }

    private String getProjectPath() {
        String path = getServletContext().getRealPath("/");

        return path.substring(0, path.length() - 11);
    }

    private String getUsersPath() {
        return getProjectPath() + "users.txt";
    }


}
