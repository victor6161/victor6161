package by.bsu.up.chat.storage;

import by.bsu.up.chat.common.models.Message;
import by.bsu.up.chat.logging.Logger;
import by.bsu.up.chat.logging.impl.Log;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        messages= new Gson().fromJson(s, generic.getClass().getGenericSuperclass());
    }

    public void write(List<Message> message) throws IOException {
        String json = new Gson().toJson(message);
        FileWriter writer = new FileWriter("log.txt", false);
        try {
            writer.write(json);
        } finally {
            writer.flush();
            writer.close();
        }

    }


    @Override
    public void addMessage(Message message) throws IOException {
        messages.add(message);
        write(messages);
    }

    @Override
    public boolean updateMessage(Message message) {
        throw new UnsupportedOperationException("Update for messages is not supported yet");
    }

    @Override
    public synchronized boolean removeMessage(String messageId) {
        throw new UnsupportedOperationException("Removing of messages is not supported yet");
    }

    @Override
    public int size() {
        return messages.size();
    }
}