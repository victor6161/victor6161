package json;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

class ChangeHistory {

    ArrayList<Message> history;
    public ChangeHistory (ArrayList<Message> h){
        history=h;
    }

    public void add(String authorName,String message) throws IOException {
        String uniqueID = UUID.randomUUID().toString();
        history.add(new Message(uniqueID, authorName, new Date().getTime(), message));
    }

    public void remove() {
        boolean flag=true;
        for (int i = 0; i < history.size(); i++) {
            if (history.get(i).getId().equals("44ce4833-591e-42b5-84b3-e52593222810")) {
                history.remove(i);
                flag=false;
            }
        }

        if (flag){
            System.out.println("Такого нет");
        }else {
            System.out.println("Удалено");
        }

    }
}
