import Exceptions.ContentException;
import Logs.Logs;
import mailBox.CreatedEmail;
import mailBox.Email;
import mailBox.MailBox;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        MailBox mailbox = new MailBox("florianalvora@gmail.com", "wfiwfrtiyocuzogq");

        mailbox.createMail(new CreatedEmail(mailbox));

        mailbox.getDrafts().get(0).updateMessage("sample message");

        Email email = mailbox.getDrafts().get(0);

        email.updateReceivers(List.of(new String[]{"florianalvora@gmail.com"}));
        email.updateMessage("coucou");
        email.updateSubject("bonjour de florian alb");

        try {
            email.sendEmail(mailbox);
        } catch (ContentException e){
            e.printStackTrace();
            Logs.writeLog(e);
        }

        System.out.println("mail " + mailbox.sentEmails);

        // TODO : menu - Mails reçus

        //mailbox.showSentEmails();
        //mailbox.showDrafts();
    }
}