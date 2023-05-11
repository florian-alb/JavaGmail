package Menu;

import mailBox.MailBox;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;

    public Menu() {
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("----- Java Gmail -----");
        System.out.println("1. Show Inbox");
        System.out.println("2. Show Drafts");
        System.out.println("3. Send a New Email");
        System.out.println("0. Exit");
    }
