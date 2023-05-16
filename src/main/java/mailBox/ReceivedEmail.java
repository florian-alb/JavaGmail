package mailBox;

import java.util.Date;
import java.util.List;

public class ReceivedEmail extends Email {
    Boolean isRead = false;
    Date receviedDate;

    public ReceivedEmail(Content content, Date receviedDate) {
        this.receviedDate = receviedDate;
        this.content = content;
    }

    public ReceivedEmail(List<String> receivers, String sender, String subject, String message, Date receviedDate) {
        this.receviedDate = receviedDate;
        this.content.setSubject(subject);
        this.content.setMessage(message);
        this.content.setSender(sender);
        this.content.setReceivers(receivers);
    }

    @Override
    public void addToInbox(MailBox mailBox) {
        mailBox.getInbox().add(this);
    }

    @Override
    public void showEmail() {
        this.isRead = true;
        System.out.println("Sender: " + this.content.getSender());
        System.out.print("Receivers: ");
        this.showReceivers();
        System.out.println("Received date: " + this.receviedDate);
        System.out.println(
                "Email Subject: " + this.content.getSubject() + "\n" +
                        "Email Content: " + this.content.getMessage() + "\n"
        );
    }

    @Override
    public String quickShow() {
        return ("\tSender: " + this.content.getSender() + "\n" +
                "\tObject: " + this.content.getSubject() + "\n" +
                "\tDate: " + this.receviedDate + "\n" +
                "\tIs Read: " + this.isRead + "\n" +
                "\tFavorite: " + this.getFavorite() + "\n" +
                "\tImportant: " + this.getImportant() + "\n"
        );
    }

}
