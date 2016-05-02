package json;

import java.io.*;
import java.util.*;

class Main {

    public static void main(String[] args) throws IOException {
        WorkingFiles fileWork = new WorkingFiles();
        ArrayList<Message> history = fileWork.read();
        ChangeHistory changeHistory = new ChangeHistory(history);
        ShowHistory showHistory = new ShowHistory(history);
        Input input=new Input();
        Scanner scanner=new Scanner(System.in);
        boolean exit=false;
        while (!exit) {
            switch (input.menu(scanner)) {
                case 1:
                    System.out.println("Введите имя");
                    String author=input.readConsole(scanner);
                    System.out.println("Введите сообщение");
                    String message=input.readConsole(scanner);
                    changeHistory.add(author,message);
                    break;
                case 2:
                    System.out.println("Введите имя файла");
                    String nameFile = input.readConsole(scanner) ;
                    fileWork.save(history, nameFile);
                    break;
                case 3:
                    showHistory.show();
                    break;
                case 4:
                    changeHistory.remove();
                    break;
                case 5:
                    System.out.println("Введите автора");
                    String authorName = input.readConsole(scanner);
                    showHistory.byAuthor(authorName);
                    break;
                case 6:
                    System.out.println("Введите ключ");
                    String keyWord = input.readConsole(scanner);
                    showHistory.byKey(keyWord);
                    break;
                case 7:
                    System.out.println("Введите регулярное выражение");
                    String regex = input.readConsole(scanner);
                    showHistory.byRegex(regex);
                    break;
                case 8:
                    exit=true;
                    fileWork.write(history);
                    scanner.close();
                    break;
                default:
                    System.out.println("Введите правильное число");
                    break;
            }

        }
    }
}
