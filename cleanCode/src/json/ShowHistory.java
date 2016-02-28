
package json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ShowHistory {

    ArrayList<Message> history;

    public ShowHistory(ArrayList<Message> h) {
        history = h;

    }

    void author(String n) {
        for (Message aMessage : history) {
            if (aMessage.getAuthor().equals(n)) {
                System.out.println(n + " написал " + aMessage.getMessage());
            }
        }
    }

    void key( String k) {
        for (Message aMessage : history) {
            if (aMessage.getMessage().contains(k)) {
                System.out.println(aMessage.getAuthor() + " написал " + aMessage.getMessage());
            }
        }
    }

    void reg() {
        Pattern pattern = Pattern.compile("[A-Z][a-z]{1,6}[!]");
        Matcher matcher;
        for (Message aMessage : history) {
            matcher = pattern.matcher(aMessage.getMessage());
            if (matcher.matches()) {
                System.out.println(aMessage.getAuthor() + " написал " + aMessage.getMessage());
            }
        }
    }
     void show() throws IOException {
        Collections.sort(history, (o1, o2) -> o1.getTime().compareTo(o2.getTime()));
        for (Message aMessage : history) {
            System.out.println(aMessage.getMessage());
        }
    }
}
