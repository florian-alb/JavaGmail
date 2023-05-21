package mailBox;

import Exceptions.ContentException;

import java.util.Date;
import java.util.List;

public class CreatedEmail extends Email {
    Date creationDate;
    Date lastUpdateDate;
    Date sendDate = null;

    /**
     * <p>Constructor of CreatedEmail</p>
     *
     * @param mailBox an instance of Mailbox
     */
    public CreatedEmail(MailBox mailBox) {
        super();
        this.content.setSender(mailBox.getMailAddress());
        this.creationDate = new Date();
        this.lastUpdateDate = this.creationDate;
        this.sendDate = null;
    }

    /**
     * <p>Constructor of CreatedEmail</p>
     *
     * @param content instance of Content
     * @param mailBox an instance of Mailbox
     */
    public CreatedEmail(Content content, MailBox mailBox) {
        this.content.setSender(mailBox.getMailAddress());
        this.creationDate = new Date();
        this.lastUpdateDate = this.creationDate;
        this.content = content;
    }

    /**
     * <p>Set the new message of the email and update the date of the last update</p>
     *
     * @param message new message
     */
    @Override
    public void updateMessage(String message) {
        this.content.setMessage(message);
        this.lastUpdateDate = new Date();
    }

    /**
     * <p>add a new receivers of the email and update the date of the last update</p>
     *
     * @param emailAddresses List of new receiver emails.
     */
    @Override
    public void updateReceivers(List<String> emailAddresses) {
        this.content.setReceivers(emailAddresses);
        this.lastUpdateDate = new Date();
    }

    /**
     * <p>Update the subject of the email and update the date of the last update</p>
     *
     * @param subject List of new receiver emails.
     */
    @Override
    public void updateSubject(String subject) {
        this.content.setSubject(subject);
        this.lastUpdateDate = new Date();
    }

    /**
     * <p>Send an email to the receivers</p>
     *
     * @param mailBox and instance of mailbox.
     * @throws ContentException an error if a receiver email address is not correct
     */
    @Override
    public void sendEmail(MailBox mailBox) throws ContentException {
        if (this.content.getReceivers() == null || this.content.getReceivers().size() == 0) {
            throw new ContentException("Email not sent: No receiver");
        }
        EmailSender.sendEmail(mailBox, this);
        this.sendDate = new Date();
        mailBox.send(this);
    }

    /**
     * <p>Send an email to the receivers</p>
     *
     * @param mailBox   and instance of mailbox.
     * @param receivers the list of receivers of this email.
     * @throws ContentException an error if a receiver email address is not correct
     */
    public void sendEmail(MailBox mailBox, List<String> receivers) throws ContentException {
        this.content.setReceivers(receivers);

        EmailSender.sendEmail(mailBox, this);
        this.sendDate = new Date();
        mailBox.send(this);
    }

    /**
     * <p>Return the important information of an email</p>
     * <p>
     * * @return the important information of an email
     */
    @Override
    public String quickShow() {
        return ("\tSender: " + this.content.getSender() + "\n"
                + "\tObject: " + this.content.getSubject() + "\n"
                + "\tSend Date: " + this.sendDate + "\n"
                + "\tFavorite: " + this.getFavorite() + "\n"
                + "\tImportant: " + this.getImportant() + "\n");
    }

}
