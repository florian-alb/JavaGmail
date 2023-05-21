package mailBox;

import java.util.Date;
import java.util.List;

public class ReceivedEmail extends Email {
    Boolean isRead = false;
    Date receivedDate;

    /**
     * <p>Constructor of ReceivedEmail</p>
     *
     * @param content      the content of this email
     * @param receivedDate the date we received this email
     */
    public ReceivedEmail(Content content, Date receivedDate) {
        this.receivedDate = receivedDate;
        this.content = content;
    }

    /**
     * <p>Constructor of ReceivedEmail</p>
     *
     * @param receivers    the list of receivers
     * @param sender       the sender of this email
     * @param subject      the subject of this email
     * @param message      the message of this email
     * @param receivedDate the date we received this email
     */
    public ReceivedEmail(List<String> receivers, String sender, String subject, String message, Date receivedDate) {
        this.receivedDate = receivedDate;
        this.content.setSubject(subject);
        this.content.setMessage(message);
        this.content.setSender(sender);
        this.content.setReceivers(receivers);
    }

    /**
     * <p>Add an email to the Inbox</p>
     *
     * @param mailBox an instance of MailBox
     */
    @Override
    public void addToInbox(MailBox mailBox) {
        mailBox.getInbox().add(this);
    }

    /**
     * <p>Show this email and his content</p>
     */
    @Override
    public void showEmail() {
        this.isRead = true;
        System.out.println("Sender: " + this.content.getSender());
        System.out.print("Receivers: ");
        this.showReceivers();
        System.out.println("Received date: " + this.receivedDate);
        System.out.println("Email Subject: " + this.content.getSubject() + "\n" + "Email Content: " + this.content.getMessage() + "\n");
    }

    /**
     * <p>Return the important information of an email</p>
     *
     * @return the important information of an email
     */
    @Override
    public String quickShow() {
        return ("\tSender: " + this.content.getSender() + "\n" +
                "\tObject: " + this.content.getSubject() + "\n" +
                "\tDate: " + this.receivedDate + "\n" +
                "\tIs Read: " + this.isRead + "\n" +
                "\tFavorite: " + this.getFavorite() +
                "\n" + "\tImportant: " + this.getImportant() + "\n");
    }

}
