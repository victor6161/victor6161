package javaa;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request,HttpServletResponse response )throws ServletException, IOException{
        System.out.println("1");
        String login=request.getParameter("username");
        String password=request.getParameter("pass");

        for (int i = 0; i< Storage.users.size(); i++) {
            if(Storage.users.get(i).getLogin().contains(login) && Storage.users.get(i).getPassword().contains(password)) {
                response.sendRedirect("/Homepage.html");
                break;
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
//java maven convention