package json;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

class WorkingFiles {
     //final String path="cleancode\\";//использовать относительные пути


    public ArrayList<Message> read() throws IOException {

        File f = new File(/*path+*/"log.txt");

        FileReader reader = new FileReader(f);
        char[] buffer = new char[(int) f.length()];
        reader.read(buffer);
        String s = new String(buffer);
        ArrayList<Message> generic = new ArrayList<Message>() {
        };
        return new Gson().fromJson(s, generic.getClass().getGenericSuperclass());
    }

    public void save(ArrayList<Message> message, String nameFile) throws IOException {
        String json = new Gson().toJson(message);

        FileWriter writer = new FileWriter(/*path +*/ nameFile, false);
        try {
            writer.write(json);
        } finally {
            writer.flush();
            writer.close();
        }
    }

    public void write(ArrayList<Message> message) throws IOException {
        String json = new Gson().toJson(message);
        FileWriter writer = new FileWriter(/*path+*/"log.txt", false);
        try {
            writer.write(json);
        } finally {
            writer.flush();
            writer.close();
        }

    }

}
