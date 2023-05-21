# JAVA GMAIL
A quick Java application to create and send email using the Gmail SMTP server.

## ⚡️ Features

- Possibility to send an Email with your Gmail mailbox
- Possibility to mark an email as favorite or as important.
- Validation of the recipients email address validity (ex: mail@domain.com )

## 🆕 Features to add

- Connection to the Gmail mailbox
    - Retrieve emails received on the mailbox
    - Edit the status of emails in the mailbox
- Add a visual interface

## 🧠 UML diagram

Before starting codint this appliacation, I decided to create an UML diagrma to organise my work and structure my ideas.

You can acces to this diagram [here](https://github.com/florian-alb/JavaGmail/blob/main/Readme/uml_diagram.pdf).

## 💻 Requirements

- Java 8 or newer
- Apache Maven 4.0 or newer
- A Gmail account

## 🛠️ Installation

#### With Github :

```bash
git clone https://github.com/florian-alb/JavaGmail
```

To use this program with your email address, please follow these steps:

1. In the main method, update the email in the MailBox constructor with your email address.

![App Screenshot](Readme/email_place.png)
2. Set a new gmail application password : [instructions here](https://support.google.com/mail/answer/185833?hl)

3. Set a new environement variable ```EMAIL_PASSWORD``` that contain your password or application password.

4. Run the program and enjoy 😎

<sup>**🥷 Privacy First:** do not use your Gmail password dirrectly on your program. Use a environement variable.</sup>

## ☕️ Java doc

You can see the Java doc on the file ```target/site/apidocs/index.html``` and update it using the following command in the application root :

```bash
mvn javadoc:javadoc
```

## 🤓 Author
ALBORA Floirian