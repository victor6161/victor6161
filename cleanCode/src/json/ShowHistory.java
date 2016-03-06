
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


    public void byAuthor( String authorName) {

        for (Message aMessage : history) {
            if (aMessage.getAuthor().equals(authorName)) {
                System.out.println(authorName + " написал " + aMessage.getMessage());
            }
        }
    }

    public void byKey(String keyWord) {

        for (Message aMessage : history) {
            if (aMessage.getMessage().contains(keyWord)) {
                System.out.println(aMessage.getAuthor() + " написал " + aMessage.getMessage());
            }
        }
    }

    public void byRegex(String regex) {


        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        for (Message aMessage : history) {
            matcher = pattern.matcher(aMessage.getMessage());
            if (matcher.matches()) {
                System.out.println(aMessage.getAuthor() + " написал " + aMessage.getMessage());
            }
        }
    }
     public void show() throws IOException {
        Collections.sort(history, (o1, o2) -> o1.getTime().compareTo(o2.getTime()));
        for (Message aMessage : history) {
            System.out.println(aMessage.getMessage());
        }
    }
}
