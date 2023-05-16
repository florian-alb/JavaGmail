package mailBox;

import Exceptions.ContentException;
import Logs.Logs;

import java.util.List;

public class Email {
    protected Content content;
    protected Boolean favorite = false;
    protected Boolean important = false;

    public Email() {
        this.content = new Content();
        this.content.setMessage("Empty message");
        this.content.setSubject("Default subject");
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public Boolean getImportant() {
        return important;
    }

    public String getSender(){
        return this.content.getSender();
    }

    public void updateMessage(String message){};

    public void addToFavorite(MailBox mailBox) {
        this.favorite = !this.favorite;
        if (this.favorite){
            mailBox.getFavorites().add(this);
            Logs.writeLog("Add to favorite", this, mailBox);
        } else {
            mailBox.getFavorites().remove(this);
            Logs.writeLog("Remove from favorite", this, mailBox);
        }
    }

    public void markAsImportant(MailBox mailBox) {
        this.important = !this.important;
        if (this.important){
            mailBox.getImportant().add(this);
            Logs.writeLog("Marked as important", this, mailBox);
        } else {
            mailBox.getImportant().remove(this);
            Logs.writeLog("Unmarked as important", this, mailBox);
        }
    }

    public void showReceivers() {
        List<String> receivers = this.content.getReceivers();
        if (receivers != null){
            for (String receiver : receivers ) {
                System.out.print(receiver + ", ");
            }
            System.out.println(" ");
        } else {
            System.out.println("No receiver");
        }
    }

    public void showEmail() {
        System.out.println("Sender: " + this.content.getSender());
        System.out.print("Receivers: ");
        this.showReceivers();
        System.out.println(
                "Email Subject: " + this.content.getSubject() + "\n" +
                        "Email Content: " + this.content.getMessage() + "\n"
        );
    }

    public void updateReceivers(List<String> emailAddresses){};

    public void updateSubject(String subject){};

    public void addToInbox(MailBox mailBox){};

    public String quickShow(){
        return null;
    };
    public void sendEmail(MailBox mailbox) throws ContentException {
        System.out.println(this.getClass());
    }
}
