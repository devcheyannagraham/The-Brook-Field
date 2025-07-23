package demo.bfims.Config;

import java.util.HashMap;

public class Response {
    private HashMap<String,String> messages = new HashMap<String,String>();

    public HashMap<String, String> getMessages() {
        return messages;
    }

    public void setMessages(HashMap<String, String> messages) {
        this.messages = messages;
    }
}
