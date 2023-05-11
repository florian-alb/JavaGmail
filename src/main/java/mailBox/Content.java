package mailBox;

import java.util.List;

class Content {
    private List<String> receivers;
    private String sender;
    private String message;
    private String subject;

    Content() {
    }

    Content(List<String> receivers, String sender, String subject, String message) {
        this.receivers = receivers;
        this.sender = sender;
        this.subject = subject;
        this.message = message;
    }

    // getters
    public List<String> getReceivers() {
        return receivers;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public String getSubject() {
        return subject;
    }

    // setters
    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }
}
