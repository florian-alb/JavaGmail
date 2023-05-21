package mailBox;

import Logs.Logs;
import Menu.Menu;
import Utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class MailBox {
    private final String mailAddress;
    private final String password;

    private List<Email> inbox;
    private List<Email> drafts;
    private List<Email> sentEmails;
    //private List<Email> favorites;
    //private List<Email> important;

    /**
     * <p>Constructor of MailBox</p>
     *
     * @param mailAddress the email address of the mailbox
     * @param password    the password of the mailbox
     */
    public MailBox(String mailAddress, String password) {
        this.mailAddress = mailAddress;
        this.password = password;
        this.inbox = new ArrayList<>();
        this.drafts = new ArrayList<>();
        this.sentEmails = new ArrayList<>();
        //this.favorites = new ArrayList<>();
        //this.important = new ArrayList<>();
    }

    // getters

    /**
     * <p>Getter of MailBox class.</p>
     *
     * @return email address of the MailBox
     */
    public String getMailAddress() {
        return mailAddress;
    }

    /**
     * <p>Getter of MailBox class.</p>
     *
     * @return the password of the MailBox
     */
    String getPassword() {
        return password;
    }

    /**
     * <p>Getter of MailBox class.</p>
     *
     * @return the inbox arrayList of Email
     */
    public List<Email> getInbox() {
        return this.inbox;
    }

    /**
     * <p>Getter of MailBox class.</p>
     *
     * @return the draft arrayList of Email
     */
    public List<Email> getDrafts() {
        return drafts;
    }

    /**
     * <p>Getter of MailBox class.</p>
     *
     * @return the sentEmails arrayList of Email
     */
    public List<Email> getSentEmails() {
        return sentEmails;
    }


    /**
     * <p>Create a ne email and call the menu to edit it</p>
     */
    public void createEmail() {
        Email newEmail = new CreatedEmail(this);
        Logs.writeLog("New email created", newEmail, this);
        Menu.editEmail(newEmail, this);
        //this.drafts.add(newEmail);
    }

    /**
     * <p>Create a ne email, set the sender and call the menu to edit it</p>
     *
     * @param sender the sender of this new email
     */
    public void createEmail(String sender) {
        Email newEmail = new CreatedEmail(this);
        Logs.writeLog("New email created", newEmail, this);
        newEmail.updateReceivers(List.of(sender));
        Menu.editEmail(newEmail, this);
    }

    /**
     * <p>Save an email to the draft List</p>
     *
     * @param email the email to save.
     */
    public void saveToDrafts(Email email) {
        this.drafts.add(email);
    }

    /**
     * <p>Send an email</p>
     *
     * @param email the email to send.
     */
    void send(Email email) {
        this.drafts.remove(email);
        this.sentEmails.add(email);
        Logs.writeLog("Email Sent", email, this);
    }

    /**
     * <p>Return the favorites emails</p>
     *
     * @return a list filtered with only favorite emails
     */
    public List<Email> getFavorites() {
        List<Email> inboxFavorites = Utils.filter(Email::getFavorite, this.getInbox());
        List<Email> sendFavorites = Utils.filter(Email::getFavorite, this.getSentEmails());

        return Stream.concat(inboxFavorites.stream(), sendFavorites.stream())
                .toList();
    }

    /**
     * <p>Return the importants emails</p>
     *
     * @return a list filtered with only important emails
     */
    public List<Email> getImportant() {
        List<Email> inboxImportant = Utils.filter(Email::getImportant, this.getInbox());
        List<Email> sendImportant = Utils.filter(Email::getImportant, this.getSentEmails());

        return Stream.concat(inboxImportant.stream(), sendImportant.stream())
                .toList();
    }
}
