package mailBox;

import Exceptions.ContentException;
import Logs.Logs;

import java.util.Date;
import java.util.List;

public class Email {
    protected Content content;
    protected Boolean favorite = false;
    protected Boolean important = false;

    /**
     * <p>Constructor of Email</p>
     */
    public Email() {
        this.content = new Content();
        this.content.setMessage("Empty message");
        this.content.setSubject("Default subject");
    }

    /**
     * <p>Getter of Email class.</p>
     *
     * @return true if an email is favorite and false if not
     */
    public Boolean getFavorite() {
        return favorite;
    }

    /**
     * <p>Getter of Email class.</p>
     *
     * @return true if an email is important and false if not
     */
    public Boolean getImportant() {
        return important;
    }


    /**
     * <p>Getter of Email class.</p>
     *
     * @return the sender of the email
     */
    public String getSender() {
        return this.content.getSender();
    }


    /**
     * <p>Set the new message of the email</p>
     *
     * @param message new message
     */
    public void updateMessage(String message) {
    }

    ;

    /**
     * <p>Set this email as favorite</p>
     *
     * @param mailBox an instance of MailBox
     */
    public void addToFavorite(MailBox mailBox) {
        this.favorite = !this.favorite;
        if (this.favorite) {
            Logs.writeLog("Add to favorite", this, mailBox);
        } else {
            Logs.writeLog("Remove from favorite", this, mailBox);
        }
    }

    /**
     * <p>Mark this email as important</p>
     *
     * @param mailBox an instance of MailBox
     */
    public void markAsImportant(MailBox mailBox) {
        this.important = !this.important;
        if (this.important) {
            Logs.writeLog("Marked as important", this, mailBox);
        } else {
            Logs.writeLog("Unmarked as important", this, mailBox);
        }
    }

    /**
     * <p>Show the receivers of this email</p>
     */
    public void showReceivers() {
        List<String> receivers = this.content.getReceivers();
        if (receivers != null) {
            for (String receiver : receivers) {
                System.out.print(receiver + ", ");
            }
            System.out.println(" ");
        } else {
            System.out.println("No receiver");
        }
    }

    /**
     * <p>Show this email and his content</p>
     */
    public void showEmail() {
        System.out.println("Sender: " + this.content.getSender());
        System.out.print("Receivers: ");
        this.showReceivers();
        System.out.println(
                "Email Subject: " + this.content.getSubject() + "\n" +
                        "Email Content: " + this.content.getMessage() + "\n"
        );
    }

    /**
     * <p>add a new receivers of the email</p>
     *
     * @param emailAddresses List of new receiver emails.
     */
    public void updateReceivers(List<String> emailAddresses) {
        for (String emailAddress : emailAddresses) {
            this.content.getReceivers().add(emailAddress);
        }
    }

    /**
     * <p>Update the subject of the email</p>
     *
     * @param subject List of new receiver emails.
     */
    public void updateSubject(String subject) {
        this.content.setSubject(subject);
    }

    /**
     * <p>Add an email to the Inbox</p>
     *
     * @param mailBox an instance of MailBox
     */
    public void addToInbox(MailBox mailBox) {
        mailBox.getInbox().add(this);
    }

    /**
     * <p>Return the important information of an email</p>
     *
     * @return the important information of an email
     */
    public String quickShow() {
        return ("\tSender: " + this.content.getSender() + "\n"
                + "\tObject: " + this.content.getSubject() + "\n"
                + "\tFavorite: " + this.favorite + "\n"
                + "\tImportant: " + this.important + "\n");
    }

    /**
     * <p>This method do nothing here. It should be overrided in the class CreatedEmail </p>
     *
     * @param mailBox and instance of mailbox.
     * @throws ContentException an error if a receiver email address is not correct
     */
    public void sendEmail(MailBox mailBox) throws ContentException {
    }
}
