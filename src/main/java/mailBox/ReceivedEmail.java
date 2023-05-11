package mailBox;

import java.util.Date;
import java.util.List;

public class ReceivedEmail extends Email{
    Boolean isRead = false;
    Date receviedDate;

    public ReceivedEmail(Content content, Date receviedDate){
        this.receviedDate = receviedDate;
        this.content = content;
    }

    public ReceivedEmail(List<String> receivers,String sender, String subject, String message, Date receviedDate){
        this.receviedDate = receviedDate;
        this.content.setSubject(subject);
        this.content.setMessage(message);
        this.content.setSender(sender);
        this.content.setReceivers(receivers);
    }
}
