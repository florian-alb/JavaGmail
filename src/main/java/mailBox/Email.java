package mailBox;

import Exceptions.ContentException;

import java.util.List;

public abstract class Email {
    protected Content content;
    protected Boolean favorite = false;
    protected Boolean important = false;

    public Email() {
        this.content = new Content();
        this.content.setMessage("Empty message");
        this.content.setSubject("Default subject");
    }

    public String getSender(){
        return this.content.getSender();
    }

    public void updateMessage(String message){};

    public void addToFavorite() {
        this.favorite = true;
    }

    public void removeFromFavorites() {
        this.favorite = false;
    }

    public void unmarkAsImportant() {
        this.important = false;
    }

    public void markAsImportant() {
        this.important = true;
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

    public void updateReceivers(List<String> strings){};

    public void updateSubject(String subject){};

    public void addToInbox(MailBox mailBox){};

    public String quickShow(){
        return null;
    };
    public void sendEmail(MailBox mailbox) throws ContentException {
        System.out.println(this.getClass());
    }
}
