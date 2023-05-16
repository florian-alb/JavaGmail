package mailBox;

import Exceptions.ContentException;

import java.util.Date;
import java.util.List;

public class CreatedEmail extends Email {
    Date creationDate;
    Date lastUpdateDate;
    Date sendDate = null;

    public CreatedEmail(MailBox mailBox){
        super();
        this.content.setSender(mailBox.getMailAddress());
        this.creationDate = new Date();
        this.lastUpdateDate = this.creationDate;
        this.sendDate = null;
    }

    public CreatedEmail(Content content, MailBox mailBox){
        this.content.setSender(mailBox.getMailAddress());
        this.creationDate = new Date();
        this.lastUpdateDate = this.creationDate;
        this.content = content;
    }

    @Override
    public void updateMessage(String message){
        this.content.setMessage(message);
        this.lastUpdateDate = new Date();
    }

    @Override
    public void updateReceivers(List<String> emailAddresses){
        this.content.setReceivers(emailAddresses);
        this.lastUpdateDate = new Date();
    }

    @Override
    public void updateSubject(String subject){
        this.content.setSubject(subject);
        this.lastUpdateDate = new Date();
    }

    @Override
    public void sendEmail(MailBox mailBox) throws ContentException {
            if (this.content.getReceivers() == null || this.content.getReceivers().size() == 0){
                throw new ContentException("Email not sent: No receiver");
            }
            EmailSender.sendEmail(mailBox, this);
            this.sendDate = new Date();
            mailBox.send(this, mailBox);
    }

    public void sendEmail(MailBox mailBox, List <String> receivers ) throws ContentException{
        this.content.setReceivers(receivers);

        EmailSender.sendEmail(mailBox, this);
        this.sendDate = new Date();
        mailBox.send(this, mailBox);
    }

    @Override
    public String quickShow(){
        return ("\tSender: " + this.content.getSender() + "\n" +
                "\tObject: " + this.content.getSubject() + "\n" +
                "\tSend Date: " + this.sendDate + "\n" +
                "\tFavorite: " + this.getFavorite() + "\n" +
                "\tImportant: " + this.getImportant() + "\n"
        );
    }

}
