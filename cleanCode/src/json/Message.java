package json;


import java.util.Date;

 class Message {
    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    private String id;

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    private String author;

    public void setTime(Long time) {
        this.time = time;
    }

    public Long getTime() {
        return time;
    }

    private Long time;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public Message(String i, String au, long t, String m) {
        id = i;
        author = au;
        time = t;
        message = m;
    }

    public Message() {
        id = "";
        author = "";
        time = new Date().getTime();
        message = "";
    }



}
