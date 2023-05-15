package mailBox;

import Exceptions.ContentException;
import Utils.Utils;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public interface EmailSender {

    public static void sendEmail(MailBox mailBox, CreatedEmail email) throws ContentException {
        // Connection properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com"); // SMTP hast, can be changed to another provider
        properties.put("mail.smtp.port", "587"); // SMTP port
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailBox.getMailAddress(), mailBox.getPassword());
            }
        });


        for (String receiver : email.content.getReceivers()) {
            try {

                // New message creation
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(mailBox.getMailAddress()));
                message.setSubject(email.content.getSubject());
                message.setText(email.content.getMessage());
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));

                // check if the receiver has a valid email
                if (!Utils.checkEmail(receiver)){
                    throw new ContentException("Email not sent: Invalid receiver email");
                }

                // Email sending
                Transport.send(message);
                System.out.println("Email sent to " + receiver);

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}

