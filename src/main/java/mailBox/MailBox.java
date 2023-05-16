package mailBox;

import Logs.Logs;
import Menu.Menu;

import java.util.ArrayList;
import java.util.List;

public class MailBox {
    private final String mailAddress;
    private final String password;

    private List<Email> inbox;
    private List<Email> drafts;
    private List<Email> sentEmails;
    private List<Email> favorites;
    private List<Email> important;


    public MailBox(String mailAddress, String password) {
        this.mailAddress = mailAddress;
        this.password = password;
        this.inbox = new ArrayList<>();
        this.drafts = new ArrayList<>();
        this.sentEmails = new ArrayList<>();
        this.favorites = new ArrayList<>();
        this.important = new ArrayList<>();
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

    public void createEmail(){
        Email newEmail = new CreatedEmail(this);
        Menu.editEmail(newEmail, this);
        //this.drafts.add(newEmail);
    }

    public void createEmail(String sender, MailBox mailBox){
        Email newEmail = new CreatedEmail(this);
        Logs.writeLog("New email created", newEmail, mailBox);
        newEmail.updateReceivers(List.of(sender));
        Menu.editEmail(newEmail, this);
    }

    public void saveToDrafts(Email email){
        this.drafts.add(email);
    }

    void send(Email email, MailBox mailBox){
        this.drafts.remove(email);
        this.sentEmails.add(email);
        Logs.writeLog("Email Sent", email, mailBox);
    }

    public void showInbox(){
        if (inbox.isEmpty()){
            System.out.println("No messages");
            return;
        }
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

    public List<Email> getFavorites() {
        return favorites;
    }

    public List<Email> getImportant() {
        return important;
    }
}
