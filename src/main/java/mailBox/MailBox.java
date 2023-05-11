package mailBox;

import java.util.ArrayList;
import java.util.List;

public class MailBox {
    private String mailAddress;
    private String password;

    private List<Email> inbox;
    private List<Email> drafts;
    public List<Email> sentEmails;

    public MailBox(String mailAddress, String password) {
        this.mailAddress = mailAddress;
        this.password = password;
        this.inbox = new ArrayList<>();
        this.drafts = new ArrayList<>();
        this.sentEmails = new ArrayList<>();
    }

    // getters
    public String getMailAddress() {
        return mailAddress;
    }

    public String getPassword() {
        return password;
    }

    public List<Email> getInbox(){
        return this.inbox;
    }

    public List<Email> getDrafts() {
        return drafts;
    }

    public List<Email> getSentEmails() {
        return sentEmails;
    }

    public void createMail(CreatedEmail email){
        this.drafts.add(email);
    }

    void send(Email email){
        //this.drafts.remove(email);
        this.sentEmails.add(email);
    }

    public void showInbox(){
        for (Email email : inbox) {
            email.showEmail();
        }
    }

    public void showDrafts(){
        for (Email email : drafts) {
            email.showEmail();
        }
    }

    public void showSentEmails(){
        for (Email email : sentEmails) {
            email.showEmail();
        }
    }
}
