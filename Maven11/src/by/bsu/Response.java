package by.bsu;


import java.util.List;

public class Response {

    private List<Message> messages;
    private String token;

    Response(List<Message> messages, String token) {
        this.messages = messages;
        this.token = token;
    }

}

