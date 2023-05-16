package Menu;

import Exceptions.ContentException;
import Exceptions.ExitException;
import Logs.Logs;
import mailBox.Email;
import mailBox.MailBox;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void displayMenu() {
        System.out.println("----- Java Gmail -----");
        System.out.println("1. Show Inbox");
        System.out.println("2. Show Drafts");
        System.out.println("3. Send a New Email");
        System.out.println("4 Show sent Emails");
        System.out.println("5 Show Favorites Emails");
        System.out.println("6 Show Important Emails");
        System.out.println("0. Exit");
    }

    public static void menu(MailBox mailBox) throws ExitException {
        displayMenu();
        int choice = readInteger(new Scanner(System.in));
        switch (choice) {
            case 1 -> showEmailList(mailBox, mailBox.getInbox(), "Inbox");
            case 2 -> showDrafts(mailBox);
            case 3 -> mailBox.createEmail();
            case 4 -> showEmailList(mailBox, mailBox.getSentEmails(), "Sent Emails");
            case 5 -> showEmailList(mailBox, mailBox.getFavorites(), "Favorites");
            case 6 -> showEmailList(mailBox, mailBox.getImportant(), "Important Emails");
            case 0 -> {
                throw new ExitException("Exiting Program");
            }
            default -> System.out.println("Invalid choice. Please try again.");
        }
    }

    private static int readInteger(Scanner scanner) {
        try {
            System.out.print("Enter your choice: ");
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter an integer.");
            scanner.nextLine(); // Clear the input buffer
            return readInteger(scanner);
        }
    }

    private static String readString(Scanner scanner, String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            System.out.println("Invalid input. Please enter a non-empty string.");
            return readString(scanner, prompt); // Recursive call
        }
        return input;
    }

    private static void showDrafts(MailBox mailBox) {
        if (mailBox.getDrafts().isEmpty()) {
            System.out.println("\nNo drafts found.\n");
        } else {
            System.out.println("----- Drafts -----");
            for (int i = 0; i < mailBox.getDrafts().size(); i++) {
                System.out.println((i + 1) + ". " + mailBox.getDrafts().get(i).quickShow());
            }
            System.out.println("-------------------");
            int index;
            do {
                System.out.print("Enter the index of the draft to edit or send or type '0' to exit: ");
                index = readInteger(new Scanner(System.in));
                if (index >= 1 && index <= mailBox.getDrafts().size()) {
                    Email email = mailBox.getDrafts().get(index - 1);
                    editEmail(email, mailBox);
                } else if (index != 0) {
                    System.out.println("Invalid index.");
                } else if (index == 0) {
                    System.out.println("Exiting...");
                }
            } while (index != 0);
        }
    }

    private static void showEmailList(MailBox mailBox, List<Email> emailList, String listType) {
        if (emailList.isEmpty()) {
            System.out.println("\nNo emails found.\n");
        } else {
            int index;
            do {
                System.out.println("----- " + listType + " -----");
                for (int i = 0; i < emailList.size(); i++) {
                    System.out.println((i + 1) + ". " + emailList.get(i).quickShow());
                }
                System.out.println("-------------------");
                System.out.print("\nEnter the index of the Email to see its content '0' to exit: ");
                index = readInteger(new Scanner(System.in));
                if (index >= 1 && index <= emailList.size()) {
                    Email email = emailList.get(index - 1);
                    email.showEmail();
                    emailActions(email, mailBox);
                } else if (index != 0) {
                    System.out.println("Invalid index.");
                }
            } while ((index != 0));
        }
    }

    static void emailActions(Email email, MailBox mailBox) {
        int choice;
        do {
            System.out.println("""
                    Type :\s
                    1 - to reply to this mail\s
                    2 - to add/remove this email to favorites\s
                    3 - to mark/unmark this email as important\s
                    4 - to exit:\s""");

            choice = readInteger(new Scanner(System.in));
            switch (choice) {
                case 1 -> mailBox.createEmail(email.getSender());
                case 2 -> email.addToFavorite(mailBox);
                case 3 -> email.markAsImportant(mailBox);
                case 4 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid input");
            }
        } while (choice != 4);
    }

    public static void editEmail(Email email, MailBox mailbox) {
        boolean exit = false;
        do {
            System.out.println("\n----- Edit Email -----");
            System.out.println("1. Edit Subject");
            System.out.println("2. Edit Content");
            System.out.println("3. Edit Receivers");
            System.out.println("4. Send this Email");
            System.out.println("0. Exit and Save");
            int choice = readInteger(new Scanner(System.in));
            switch (choice) {
                case 1 -> {
                    String newSubject = readString(new Scanner(System.in), "Enter the subject: ");

                    // Update the email's object
                    email.updateSubject(newSubject);
                    System.out.println("Subject updated: " + newSubject);
                }
                case 2 -> {
                    String newContent = readString(new Scanner(System.in), "Enter the content: ");

                    // Update the email's content
                    email.updateMessage(newContent);
                    System.out.println("Content updated: " + newContent);
                }
                case 3 -> {
                    String newReceivers = readString(new Scanner(System.in), "Enter the receivers (separate them from commas): ");
                    List<String> receivers = List.of(newReceivers.split(","));

                    // Update the email's receivers
                    email.updateReceivers(receivers);
                    System.out.println("Receivers updated: " + newReceivers);
                }
                case 4 -> {
                    try {
                        email.sendEmail(mailbox);
                    } catch (ContentException e) {
                        e.printStackTrace();
                        Logs.writeLog(e);
                    }
                    exit = true;
                }
                case 0 -> {
                    System.out.println("Draft Saved");

                    if (!mailbox.getDrafts().contains(email)) {
                        mailbox.saveToDrafts(email);
                    }
                    exit = true;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (!exit);
    }
}
