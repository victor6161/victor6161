package by.bsu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {

    protected void doPost(HttpServletRequest request,HttpServletResponse response )throws ServletException, IOException{


        String login=request.getParameter("username");
        String password=request.getParameter("pass");
        boolean flag=true;
        for (int i = 0; i<new Storage().usersList.size(); i++) {
            if(new Storage().usersList.get(i).getLogin().contains(login) && new Storage().usersList.get(i).getPassword().contains(password)) {
                response.sendRedirect("/defchat.html");
                flag=false;
                break;
            }
        }
        if(flag){
            response.sendRedirect("/index.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
//java maven convention