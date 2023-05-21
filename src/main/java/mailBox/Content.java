package mailBox;

import java.util.List;

class Content {
    private List<String> receivers;
    private String sender;
    private String message;
    private String subject;

    /**
     * <p>Constructor of Content</p>
     */
    Content() {
    }


    /**
     * <p>Constructor of Content</p>
     *
     * @param receivers a lift of email addresses
     * @param sender    the email address of the sender of the email
     * @param message   the message to send
     * @param subject   the subject of the email
     */
    Content(List<String> receivers, String sender, String subject, String message) {
        this.receivers = receivers;
        this.sender = sender;
        this.subject = subject;
        this.message = message;
    }

    // getters

    /**
     * <p>Getter of Content class.</p>
     *
     * @return a list of email addresses
     */
    public List<String> getReceivers() {
        return receivers;
    }

    /**
     * <p>add a new receivers of the email</p>
     *
     * @param receivers List of new receiver emails.
     */
    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }

    /**
     * <p>Getter of Content class.</p>
     *
     * @return the sender of an email
     */
    public String getSender() {
        return sender;
    }

    /**
     * <p>Set the new sender of the email</p>
     *
     * @param sender new sender
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    // setters

    /**
     * <p>Getter of Content class.</p>
     *
     * @return the message of an email
     */
    public String getMessage() {
        return message;
    }

    /**
     * <p>Set the new message of the email</p>
     *
     * @param message new message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * <p>Getter of Content class.</p>
     *
     * @return the subject of an email
     */
    public String getSubject() {
        return subject;
    }

    /**
     * <p>Set the new subject of the email</p>
     *
     * @param subject new sender
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
}
