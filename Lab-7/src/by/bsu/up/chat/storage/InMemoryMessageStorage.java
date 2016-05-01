package by.bsu.up.chat.storage;

import by.bsu.up.chat.common.models.Message;
import by.bsu.up.chat.logging.Logger;
import by.bsu.up.chat.logging.impl.Log;
import com.google.gson.Gson;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InMemoryMessageStorage implements MessageStorage {

    private static final String DEFAULT_PERSISTENCE_FILE = "messages.srg";

    private static final Logger logger = Log.create(InMemoryMessageStorage.class);

    private List<Message> messages = new ArrayList<>();

    @Override
    public synchronized List<Message> getPortion(Portion portion) {
        int from = portion.getFromIndex();
        if (from < 0) {
            throw new IllegalArgumentException(String.format("Portion from index %d can not be less then 0", from));
        }
        int to = portion.getToIndex();
        if (to != -1 && to < portion.getFromIndex()) {
            throw new IllegalArgumentException(String.format("Porting last index %d can not be less then start index %d", to, from));
        }
        to = Math.max(to, messages.size());
        return messages.subList(from, to);
    }

    public void read() throws IOException {
        File f = new File("log.txt");
        FileReader reader = new FileReader(f);
        char[] buffer = new char[(int) f.length()];
        reader.read(buffer);
        String s = new String(buffer);
        ArrayList<Message> generic = new ArrayList<Message>() {
        };
        messages = new Gson().fromJson(s, generic.getClass().getGenericSuperclass());
    }

    public void write(List<Message> message) throws IOException {
        FileWriter writer = new FileWriter("log.txt");
        JsonWriter jsonWriter = new JsonWriter(writer);
        jsonWriter.beginArray();
        for (int i = 0; i < message.size(); i++) {
            jsonWriter.beginObject();
            jsonWriter.name("id").value(message.get(i).getId());
            jsonWriter.name("author").value(message.get(i).getAuthor());
            jsonWriter.name("text").value(message.get(i).getText());
            jsonWriter.name("timestamp").value(message.get(i).getTimestamp());
            jsonWriter.endObject();
        }
        jsonWriter.endArray();
        jsonWriter.close();
        writer.close();
    }

    @Override
    public void addMessage(Message message) throws IOException {
        messages.add(message);
        write(messages);
    }

    @Override
    public boolean updateMessage(String messageId, String text) throws IOException {
        for (int i = 0; i < messages.size(); i++) {
            if (messages.get(i).getId().equals(messageId)) {
                messages.get(i).setText(text);
                write(messages);
                return true;
            }
        }
        return false;
    }

    @Override
    public synchronized boolean removeMessage(String messageId) throws IOException {
        for (int i = 0; i < messages.size(); i++) {

            if (messages.get(i).getId().equals(messageId)) {

                messages.remove(i);
                write(messages);
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return messages.size();
    }
}
