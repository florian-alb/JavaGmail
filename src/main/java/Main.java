import Exceptions.ExitException;
import Logs.Logs;
import Menu.Menu;
import mailBox.Email;
import mailBox.MailBox;
import mailBox.ReceivedEmail;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final String emailPassword = System.getenv("EMAIL_PASSWORD");

        MailBox mailBox = new MailBox("florianalvora@gmail.com", emailPassword);

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

        email1.addToFavorite(mailBox);
        email1.markAsImportant(mailBox);
        email1.addToInbox(mailBox);
        email2.addToInbox(mailBox);
        email3.addToInbox(mailBox);
        email4.addToInbox(mailBox);
        email5.addToInbox(mailBox);

        boolean exit = false;

        do {
            try {
                Menu.menu(mailBox);
            } catch (ExitException e) {
                exit = true;
                e.printStackTrace();
                Logs.writeLog(e);
            }
        } while (!exit);
    }
}