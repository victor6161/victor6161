//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package by.bsu;


import com.google.gson.stream.JsonWriter;
import java.io.*;
import java.util.*;


public class MessageHistory {

    private List<Message> messages = new ArrayList<Message>();
    public int size() {
        return this.data.size();
    }
    public static final ArrayList<Message> data
            = new ArrayList<Message>(Arrays.asList(
            new Message("ivanov"),
            new Message("petrov"),
            new Message("sdf")
    ));
    public List<Message> getPortion(int index) {
        ArrayList portion = new ArrayList();

        for(int i = index; i < this.data.size(); ++i) {
            portion.add(this.data.get(i));
        }


        return portion;
    }


    public void write(List<Message> message) throws IOException {
        FileWriter writer = new FileWriter("log.txt");
        JsonWriter jsonWriter = new JsonWriter(writer);
        jsonWriter.beginArray();
        for (int i = 0; i < message.size(); i++) {
            jsonWriter.beginObject();
            jsonWriter.name("id").value(message.get(i).getId());
            jsonWriter.name("author").value(message.get(i).getAuthor());
            jsonWriter.name("text").value(message.get(i).getMessage());
            jsonWriter.endObject();
        }
        jsonWriter.endArray();
        jsonWriter.close();
        writer.close();
    }



    public void addMessage(Message message) throws IOException {
        messages.add(message);
        write(messages);
    }


    }







