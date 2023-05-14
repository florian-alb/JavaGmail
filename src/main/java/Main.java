import Exceptions.ContentException;
import Exceptions.ExitException;
import Logs.Logs;
import Menu.Menu;
import mailBox.CreatedEmail;
import mailBox.Email;
import mailBox.MailBox;
import mailBox.ReceivedEmail;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MailBox mailbox = new MailBox("florianalvora@gmail.com", "wfiwfrtiyocuzogq");

        Email email1 = new ReceivedEmail(
                List.of("florianalvora@gmail.com"),
                "noreply@example.com",
                "Hello",
                "Hi there! How are you?",
                new Date()
        );

        Email email2 = new ReceivedEmail(
                List.of("florianalvora@gmail.com"),
                "noreply@example.com",
                "Meeting Reminder",
                "Just a reminder that we have a meeting tomorrow at 10 AM.",
                new Date()
        );

        Email email3 = new ReceivedEmail(
                List.of("florianalvora@gmail.com"),
                "bob@example.com",
                "Important Announcement",
                "Please be informed that the office will be closed on Monday.",
                new Date()
        );

        Email email4 = new ReceivedEmail(
                List.of("florianalvora@gmail.com"),
                "mary@example.com",
                "Technical Support Request",
                "I'm having trouble with my account. Can you please assist me?",
                new Date()
        );

        Email email5 = new ReceivedEmail(
                List.of("florianalvora@gmail.com"),
                "support@example.com",
                "Product Inquiry",
                "I'm interested in learning more about your new product line.",
                new Date()
        );

        email1.addToInbox(mailbox);
        email2.addToInbox(mailbox);
        email3.addToInbox(mailbox);
        email4.addToInbox(mailbox);
        email5.addToInbox(mailbox);

        boolean exit = false;

        do {
            try {
                Menu.menu(mailbox);
            } catch (ExitException e) {
                exit = true;
                e.printStackTrace();
                Logs.writeLog(e);
            }
        } while (!exit) ;

        // TODO : Mails reçus
    }
}