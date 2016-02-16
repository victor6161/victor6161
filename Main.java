package json;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;


public class Main {
    private static int k = 0;

    public int menu() {
        System.out.print("1.добавить сообщение\n2.загрузка/сохранение сообщений из файла/в файл\n3.просмотр истории");
        System.out.print(" в хронологическом порядке\n4.удаление сообщения по идентификатору\n");
        int i = 0;
        Scanner sc = new Scanner(System.in);
        if (sc.hasNext()) {
            i = sc.nextInt();
        }
        return i;
    }

    public StringBuffer addMessage(StringBuffer s) throws IOException {

        System.out.println("Введите имя");
        Scanner name = new Scanner(System.in);
        String n;
        n = name.nextLine();
        System.out.println("Введите сообщение");
        Scanner mess = new Scanner(System.in);
        String m;
        m = mess.nextLine();
        String uniqueID = UUID.randomUUID().toString();
        Message message = new Message(uniqueID, n, new Date().getTime(), m);
        Gson gson = new GsonBuilder().create();
        gson.toJson(message, s);
        return s;

    }


    private void show(StringBuffer s) throws IOException {

        Gson gson = new GsonBuilder().create();

        String sb = s.toString();
        Message[] p = gson.fromJson(sb, Message[].class);

        for (int i = 0; i < p.length; i++) {
            System.out.println(p[i].getMessage());
        }

        /*
        Arrays.sort(p, new Comparator<Message>() {
            public int compare(final Message o1,final Message o2) {
                return o1.getTime().compareTo(o2.getTime());
            }
        });*/


    }


    private void load(StringBuffer s) throws IOException {
        FileWriter writer = new FileWriter("C:\\Users\\victo\\work folder\\cleancode\\json\\log1.txt", false);
        String sb = s.toString();
        writer.write(sb);
        writer.flush();
        writer.close();
    }

    private StringBuffer delete(StringBuffer s) {
        Gson gson = new GsonBuilder().create();
        String sb = s.toString();
        ArrayList<Message> items_generic = new ArrayList<Message>() { };
        ArrayList<Message> items = gson.fromJson(sb, items_generic.getClass().getGenericSuperclass());
        System.out.println(items);
        for (int i=0;i<items.size();i++){
            if(items.get(i).getId().equals("46f408b2-72cb-4307-b6e6-95a8515eb7c0")){
                items.remove(i);
            }
        }
         sb=items.toString();
        System.out.println(sb);
        s = new StringBuffer(sb.subSequence(0, sb.length()));

        return s;
    }

    private StringBuffer read() {
        StringBuffer s = new StringBuffer();
        Gson gson = new GsonBuilder().create();
        File f = new File("C:\\Users\\victo\\work folder\\cleancode\\json\\log.txt");
        try (FileReader reader = new FileReader(f)) {

            char[] buffer = new char[(int) f.length()];

            reader.read(buffer);
            s = new StringBuffer(String.valueOf(buffer));


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return s;
    }


    private void write(StringBuffer s) throws IOException {
        Gson gson = new GsonBuilder().create();
        int i = s.lastIndexOf("]");
        s.replace(i, i + 1, ",");

        Message[] message = new Message[0];
        gson.toJson(message, s);
        i = s.lastIndexOf("[");
        s.deleteCharAt(i);

        FileWriter writer = new FileWriter("C:\\Users\\victo\\work folder\\cleancode\\json\\log.txt", false);
        String sb = s.toString();
        writer.write(sb);
        writer.flush();
        writer.close();

    }




    public static void main(String[] args) throws IOException {
        StringBuffer s;
        Main m = new Main();
        while (true) {
            s = m.read();
            boolean exit = false;
            switch (m.menu()) {
                case 1:
                    s = m.addMessage(s);
                    break;
                case 2:
                    m.load(s);
                    break;
                case 3:
                    m.show(s);
                    break;
                case 4:
                    s=m.delete(s);
                    break;
                default:
                    System.out.println("Введите правильное число");
                    break;
            }
             m.write(s);
             break;
        }

        //Gson gson = new GsonBuilder().create();
        //Writer writer = new FileWriter("Output.txt");

        //gson.toJson("Hello", writer);
        //gson.toJson(123, writer);

        //writer.close();
    }


}
