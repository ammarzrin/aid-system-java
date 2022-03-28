package roles;
// User authentication methods are stored here, applicable to both NGO and Donor users.

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Account {
    private String username;
    private String password;

    public Account() {
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public static void displayHeader(String message) {
        System.out.println();
        int width = message.length() + 2;
        StringBuilder sb = new StringBuilder();
        sb.append("+");
        for (int i = 0; i < width; ++i) {
            sb.append("-");
        }
        sb.append("+");
        System.out.println(sb.toString());
        System.out.println("| " + message + " |");
        System.out.println(sb.toString());
    }

    public static int getMenuInput() {
        try (Scanner input = new Scanner(System.in)) {
            int choice = 0;
            do {
                System.out.print("Enter choice --> ");
                try {
                    choice = input.nextInt();
                    if (choice < 1 || choice > 6)
                        System.out.println("Please enter an integer from 1 to 6.");
                } catch (InputMismatchException e) {
                    System.out.println("Please enter an integer!");
                    input.nextInt();
                }
            } while (choice < 1 || choice > 6);
            return choice;
        }
    }

    public static boolean logout() {
        System.out.println("Logging you out...");
        return false;
    }

    public static void viewAllDonations() {
        System.out.println("Let's view all donations made so far.");
    }
}