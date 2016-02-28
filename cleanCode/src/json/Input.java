package json;

import java.util.Scanner;

public class Input {

    Scanner read;
    int menu() {
        System.out.print("1.Добавить сообщение\n2.Загрузка/сохранение сообщений из файла/в файл\n3.Просмотр истории " +
                "в хронологическом порядке\n4.Удаление сообщения по идентификатору\n5.Поиск в истории сообщения по автору\n6." +
                "Поиск в истории сообщения по ключевому слову (лексеме целиком)\n7.Поиск в истории сообщения по регулярному выражению\n");
        int i;
        Scanner sc = new Scanner(System.in);
        i = sc.nextInt();
        return i;
    }
    String scan() {
        read = new Scanner(System.in);
        String n;
        n = read.nextLine();

        return n;
    }

    void close(){
        read.close();
    }

}
