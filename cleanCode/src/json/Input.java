package json;

import java.util.Scanner;

public class Input {

    public String readConsole(Scanner scanner) {

        return scanner.nextLine();
    }


    public int menu(Scanner scanner) {
        System.out.print("1.Добавить сообщение\n2.Сохранение сообщений в файл\n3.Просмотр истории " +
                "в хронологическом порядке\n4.Удаление сообщения по идентификатору\n5.Поиск в истории сообщения по автору\n6." +
                "Поиск в истории сообщения по ключевому слову (лексеме целиком)\n7.Поиск в истории сообщения по регулярному выражению\n8.Выход\n");

        scanner = new Scanner(System.in);
        if(scanner.hasNextInt()) {
            return scanner.nextInt();

        }else {
            return 0;
        }
    }

}
