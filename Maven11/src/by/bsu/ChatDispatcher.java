package by.bsu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class ChatDispatcher extends HttpServlet {
    private MessageHistory messageStorage = new MessageHistory();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        responseWithUpdates(req.getParameter("token"), resp);
     }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       Gson gson = new GsonBuilder().create();
        Message receivedMessage = gson.fromJson(req.getReader(), Message.class);
        messageStorage.addMessage(receivedMessage);
    }

    private void responseWithUpdates(String token, HttpServletResponse response) throws IOException {
        int index = getIndex(token);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Response resp = new Response(messageStorage.getPortion(index), getNewToken());
        System.out.println(new Gson().toJson(resp));
        response.getWriter().write(new Gson().toJson(resp));
    }

    private int getIndex(String token) {
        String encodedIndex = token.substring(2, token.length() - 2);
        int tokenValue = Integer.valueOf(encodedIndex);
        return decodeIndex(tokenValue);
    }

    private String getNewToken() {
        return "TN" + Integer.toString(encodeIndex(messageStorage.size())) + "EN";
    }

    private int encodeIndex(int receivedMessagesCount) {
        return receivedMessagesCount * 8 + 11;
    }
    private int decodeIndex(int stateCode) {
        return (stateCode - 11) / 8;
    }
  /*  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.print(request.getQueryString());
        System.out.print(request.getParameterNames());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String absolutePath = getServletContext().getRealPath("/");
        //System.out.println(absolutePath);
        //System.out.println(absolutePath.substring(0,absolutePath.length()-10));
        //System.out.println(read(absolutePath));
       // return  Response.ok(read(absolutePath));
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().print(Response.ok(absolutePath));
        //response.addHeader("Access-Control-Allow-Origin");
    }


    public String read(String path) throws IOException {

        File f = new File(path.substring(0,path.length()-10)+"log.txt");

        FileReader reader = new FileReader(f);
        char[] buffer = new char[(int) f.length()];
        reader.read(buffer);
        String s = new String(buffer);
        return s;
//        ArrayList<Message> generic = new ArrayList<Message>() {
  //      };
    //    return new Gson().fromJson(s, generic.getClass().getGenericSuperclass()).toString();
    }*/
}
