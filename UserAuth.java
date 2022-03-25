// Main program is run here.

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import roles.Account;
import roles.Donor;
import roles.NGO;

public class UserAuth {

    Scanner input = new Scanner(System.in);

    public UserAuth() {
        welcomePage();
    }

    void welcomePage() {
        System.out.println("Welcome, user.");
        System.out.println("Select an option:");
        System.out.println("1. Log In");
        System.out.println("2. Create an Account");
        boolean programRun = true;
        do {
            try {
                System.out.print("Enter choice --> ");
                int choice = input.nextInt();
                while (choice != 1 && choice != 2) {
                    System.out.println("Invalid input. Please enter 1 or 2.");
                    System.out.print("Enter choice --> ");
                    choice = input.nextInt();
                }
                if (choice == 1) {
                    loginAccount();
                    programRun = false;
                } else if (choice == 2) {
                    createAccount();
                    programRun = false;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input. Please enter an integer.");
                input.next();
            }
        } while (programRun == true);
    }

    void loginAccount() {
        System.out.println("Login page is displayed.");
    }

    void createAccount() {
        System.out.println("Create an account!");
        System.out.println("Are you here to donate or to represent an NGO?");
        System.out.println("I am a...");
        System.out.println("1. Donor");
        System.out.println("2. NGO Representative");
        try {
            System.out.print("Enter choice --> ");
            int choice = input.nextInt();
            while (choice != 1 && choice != 2) {
                System.out.println("Invalid input. Please enter 1 or 2.");
                System.out.print("Enter choice --> ");
                choice = input.nextInt();
            }
            if (choice == 1) {
                System.out.println("You have chosen Donor.");
                donorRegister();
            } else if (choice == 2) {
                System.out.println("You have chosen NGO.");
                ngoRegister();
            }
        } catch (InputMismatchException ex) {
            System.out.println("Invalid input. Please enter an integer.");
            input.next();
        }
    }

    void donorRegister() {
        try {
            Donor d = new Donor();
            OutputStream output = new BufferedOutputStream(new FileOutputStream("userdata/donors.csv", true));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            System.out.println("--- Login Credentials --- ");
            System.out.print("Enter a username: ");
            checkUsername(input.next(), d);
            System.out.print("Enter a password: ");
            d.setPassword(input.next());
            System.out.println("--- Personal Details --- ");
            System.out.print("Enter your name: ");
            d.setName(input.next());
            System.out.print("Enter your phone number (E.g. 012-3456789): ");
            checkPhone(input.next(), d);
            writer.write(d.getUsername() + "," + d.getPassword() + "," + d.getName() + "," + d.getPhone());
            writer.newLine();
            System.out.println("New Donor account successfully created. Happy Donating!");
            writer.close();
            output.close();
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    void ngoRegister() {
        try {
            NGO n = new NGO();
            OutputStream output = new BufferedOutputStream(new FileOutputStream("userdata/ngos.csv", true));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            System.out.println("--- Login Credentials --- ");
            System.out.print("Enter a username: ");
            checkUsername(input.next(), n);
            // n.setUsername(input.next());
            System.out.print("Enter a password: ");
            n.setPassword(input.next());
            System.out.println("--- Personal Details --- ");
            System.out.print("Enter your NGO's initials: ");
            n.setNGO(input.next());
            System.out.print("Enter your NGO's manpower number: ");
            n.setManpower(input.nextInt());

            writer.write(n.getUsername() + "," + n.getPassword() + "," + n.getNGO() + "," + n.getManpower());
            writer.newLine();
            System.out.println("New NGO account successfully created. You can start requesting!");
            writer.close();
            output.close();
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    boolean phoneValid(String phone) {
        Pattern p = Pattern.compile("^01\\d-\\d{7,8}$");
        Matcher m = p.matcher(phone);
        return (m.matches());
    }

    void checkPhone(String phonenum, Donor d) {
        if (phoneValid(phonenum)) {
            d.setPhone(phonenum);
        } else {
            System.out.println("Invalid phone number!");
            System.out.print("Re-enter your phone number (E.g. 012-3456789): ");
            checkPhone(input.next(), d);
        }
    }

    private void checkUsername(String username, Account role) throws IOException {
        // Check uniqueness of username.
        ArrayList<String> users = readUsernames();
        boolean takenUser = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(username)) {
                takenUser = true;
                break;
            } else {
                takenUser = false;
            }
        }
        if (takenUser) {
            System.out.println("Username is already taken!");
            System.out.print("Enter a new username: ");
            checkUsername(input.next(), role);
        } else {
            System.out.println("Good to go!");
            role.setUsername(username);
        }

    }

    private static ArrayList<String> readUsernames() throws IOException {
        ArrayList<String> users = new ArrayList<>();
        // read donors.csv into a list of lines.
        List<String> donors = Files.readAllLines(Paths.get("userdata/donors.csv"));
        // init at 1 to ignore column name
        for (int i = 1; i < donors.size(); i++) {
            // split a line by comma
            String[] items = donors.get(i).split(",");
            // items[0] is username
            users.add(items[0]);
        }
        // read ngos.csv into a list of lines.
        List<String> ngos = Files.readAllLines(Paths.get("userdata/ngos.csv"));
        for (int i = 1; i < ngos.size(); i++) {
            String[] items = ngos.get(i).split(",");
            users.add(items[0]);
        }
        return users;
    }
}