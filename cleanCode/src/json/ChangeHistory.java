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
    void add(String n, String m) throws IOException {
        String uniqueID = UUID.randomUUID().toString();
        history.add(new Message(uniqueID, n, new Date().getTime(), m));
    }

    ArrayList<Message> remove(ArrayList<Message> items) {
        boolean flag=true;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals("44ce4833-591e-42b5-84b3-e52593222810")) {
                items.remove(i);
                flag=false;
            }
        }

        if (flag){
            System.out.println("Такого нет");
        }else {
            System.out.println("Удалено");
        }
        return items;
    }
}
