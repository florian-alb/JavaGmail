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
        System.out.println("0. Exit");
    }

    public static void menu(MailBox mailBox) throws ExitException {
        displayMenu();
        int choice = readInteger(new Scanner(System.in));
        switch (choice) {
            case 1 -> showInbox(mailBox);
            case 2 -> showDrafts(mailBox);
            case 3 -> mailBox.createEmail();
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
            System.out.println("No drafts found.");
        } else {
            System.out.println("----- Drafts -----");
            for (int i = 0; i < mailBox.getDrafts().size(); i++) {
                System.out.println((i + 1) + ". " + mailBox.getDrafts().get(i));
            }
            System.out.println("-------------------");
            System.out.print("Enter the index of the draft to edit or send or type '-1' to exit: ");
            int index = readInteger(new Scanner(System.in));
            if (index >= 1 && index <= mailBox.getDrafts().size()) {
                Email email = mailBox.getDrafts().get(index - 1);
                editEmail(email, mailBox);
            } else if (index == -1) {
                return;
            } else {
                System.out.println("Invalid index.");
            }
        }
    }

    private static void showInbox(MailBox mailBox) {
        if (mailBox.getInbox().isEmpty()) {
            System.out.println("No Emails found.");
        } else {
            System.out.println("----- Inbox -----");
            for (int i = 0; i < mailBox.getInbox().size(); i++) {
                System.out.println((i + 1) + ". " + mailBox.getInbox().get(i).quickShow());
            }
            System.out.println("-------------------");
            System.out.print("Enter the index of the Email to see its content '-1' to exit: ");
            int index = readInteger(new Scanner(System.in));
            if (index >= 1 && index <= mailBox.getInbox().size()) {
                Email email = mailBox.getInbox().get(index - 1);
                email.showEmail();
                replyOrExit(email, mailBox);

            } else if (index == -1) {
                return;
            } else {
                System.out.println("Invalid index.");
            }
        }
    }

    static void replyOrExit(Email email, MailBox mailBox){
        System.out.print("Type 1 to reply to this mail and 2 to exit: ");
        int choice = readInteger(new Scanner(System.in));
        switch (choice){
            case 1 -> mailBox.createEmail(email.getSender());
            case 2 -> System.out.println("Exiting...");
            default -> System.out.println("Invalid input");
        }
    }

    public static void editEmail(Email email, MailBox mailbox) {
        boolean exit = false;
        while (!exit) {
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
                    mailbox.saveToDrafts(email);
                    exit = true;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
