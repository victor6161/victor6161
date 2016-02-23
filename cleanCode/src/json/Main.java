package json;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Main {

    private ArrayList<Message> read() throws IOException {
        Gson gson = new GsonBuilder().create();
        File f = new File("C:\\Users\\victo\\work folder\\cleancode\\log.txt");

        FileReader reader = new FileReader(f);
        char[] buffer = new char[(int) f.length()];
        reader.read(buffer);
        String s = new String(buffer);
        ArrayList<Message> generic = new ArrayList<Message>() {
        };
        return gson.fromJson(s, generic.getClass().getGenericSuperclass());
    }

    public int menu() {
        System.out.print("1.Добавить сообщение\n2.Загрузка/сохранение сообщений из файла/в файл\n3.Просмотр истории " +
                "в хронологическом порядке\n4.Удаление сообщения по идентификатору\n5.Поиск в истории сообщения по автору\n6." +
                "Поиск в истории сообщения по ключевому слову (лексеме целиком)\n7.Поиск в истории сообщения по регулярному выражению\n");
        int i;
        Scanner sc = new Scanner(System.in);
        i = sc.nextInt();
        return i;
    }

    public Message addMessage() throws IOException {
        System.out.println("Введите имя");
        Scanner name = new Scanner(System.in);
        String n;
        n = name.nextLine();
        System.out.println("Введите сообщение");
        Scanner mess = new Scanner(System.in);
        String m;
        m = mess.nextLine();
        String uniqueID = UUID.randomUUID().toString();
        return new Message(uniqueID, n, new Date().getTime(), m);
    }

    private void show(ArrayList<Message> items) throws IOException {
        Collections.sort(items, (o1, o2) -> o1.getTime().compareTo(o2.getTime()));
        for (Message item : items) {
            System.out.println(item.getMessage());
        }
    }

    private void load(ArrayList<Message> message) throws IOException {
        String json = new Gson().toJson(message);
        FileWriter writer = new FileWriter("C:\\Users\\victo\\work folder\\cleancode\\log1.txt", false);
        writer.write(json);
        writer.flush();
        writer.close();
    }

    private ArrayList<Message> delete(ArrayList<Message> items) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals("0b7dfcd4-9056-4cd5-9341-bd33fe449dcd")) {
                items.remove(i);
            }
        }
        return items;
    }

    private void write(ArrayList<Message> message) throws IOException {
        String json = new Gson().toJson(message);
        FileWriter writer = new FileWriter("C:\\Users\\victo\\work folder\\cleancode\\log.txt", false);
        writer.write(json);
        writer.flush();
        writer.close();
    }

    private void searchAuthor(ArrayList<Message> message) {
        System.out.println("введите автора");
        Scanner name = new Scanner(System.in);
        String n;
        n = name.nextLine();
        for (Message aMessage : message) {
            if (aMessage.getAuthor().equals(n)) {
                System.out.println(n + " написал " + aMessage.getMessage());
            }
        }
    }

    private void searchKey(ArrayList<Message> message) {
        System.out.println("введите ключевое слово");
        Scanner key = new Scanner(System.in);
        String k;
        k = key.nextLine();
        for (Message aMessage : message) {
            if (aMessage.getMessage().contains(k)) {
                System.out.println(aMessage.getAuthor() + " написал " + aMessage.getMessage());
            }
        }
    }

    private void searchReg(ArrayList<Message> message) {
        Pattern pattern = Pattern.compile("[A-Z][a-z]{1,6}[!]");
        Matcher matcher;
        for (Message aMessage : message) {
            matcher = pattern.matcher(aMessage.getMessage());
            if (matcher.matches()) {
                System.out.println(aMessage.getAuthor() + " написал " + aMessage.getMessage());
            }
        }
    }




    public static void main(String[] args) throws IOException {
        ArrayList<Message> messages;
        Main m = new Main();

            messages = m.read();
            switch (m.menu()) {
                case 1:
                    messages.add(m.addMessage());
                    break;
                case 2:
                    m.load(messages);
                    break;
                case 3:
                    m.show(messages);
                    break;
                case 4:
                    messages = m.delete(messages);
                    break;
                case 5:
                    m.searchAuthor(messages);
                    break;
                case 6:
                    m.searchKey(messages);
                    break;
                case 7:
                    m.searchReg(messages);
                    break;
                default:
                    System.out.println("Введите правильное число");
                    break;
            }
            m.write(messages);


    }


}
