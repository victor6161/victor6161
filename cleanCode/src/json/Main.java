package json;


import java.io.*;
import java.util.*;


class Main {


    public static void main(String[] args) throws IOException {


        WorkingFiles fileWork = new WorkingFiles();
        ArrayList<Message> history = fileWork.read();
        ChangeHistory changeHistory = new ChangeHistory(history);
        ShowHistory showHistory = new ShowHistory(history);
        Input input = new Input();
        switch (input.menu()) {
            case 1:
                System.out.println("Введите имя");
                String name = input.scan();
                System.out.println("Введите сообщение");
                String message = input.scan();
                changeHistory.add(name, message);
                input.close();
                break;
            case 2:
                System.out.println("Введите имя файла");
                String file = input.scan();
                fileWork.save(history, file);
                input.close();
                break;
            case 3:
                showHistory.show();
                break;
            case 4:
                changeHistory.remove(history);
                break;
            case 5:
                System.out.println("Введите автора");
                String author = input.scan();
                showHistory.author(author);
                input.close();
                // var author = readAuthor(scanner);
                // history.searchBy(author);
                break;
            case 6:
                System.out.println("Введите ключ");
                String key = input.scan();
                showHistory.key(key);
                input.close();
                break;
            case 7:
                showHistory.reg();
                break;
            default:
                System.out.println("Введите правильное число");
                break;
        }
        fileWork.write(history);
    }
}
