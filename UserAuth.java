// Main program is run here.

import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
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
        AidSystem.displayHeader("Welcome!");
        boolean loggedIn = false;
        do {
            System.out.println("Select an option:");
            System.out.println("1. Log In");
            System.out.println("2. Create an Account");
            System.out.print("Enter choice --> ");
            try {
                int choice = input.nextInt();
                while (choice != 1 && choice != 2) {
                    System.out.println("Invalid input. Please enter 1 or 2.");
                    System.out.print("Enter choice --> ");
                    choice = input.nextInt();
                }
                if (choice == 1) {
                    loggedIn = loginAccount();
                } else if (choice == 2) {
                    createAccount();
                    loggedIn = false;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input. Please enter an integer.");
                input.next();
            }
        } while (!loggedIn);
    }

    // NEED HELP!
    public void setCurrentUser(Account role) {
        // ArrayList<Account> users = new ArrayList<>();
    }

    // NEED HELP!
    public boolean loginAccount() {
        AidSystem.displayHeader("User Login");
        String username, password;
        try {
            System.out.print("Enter username: ");
            username = input.next();
            System.out.print("Enter password: ");
            password = input.next();
            checkLogin(username, password);
            // if (checkLogin(username, password)) {
            // // If checkUserType = 1 (NGO), 0 (Donor)
            // if (checkUserType(username) == 0) { // After this if statement, user breaks
            // out of
            // // loop and considered as logged in.
            // // need help getting this public & extracting user information
            // // set public current user object here
            // Donor currentUser = new Donor();
            // } else { // checkUserType(username) == 1
            // NGO currentUser = new NGO();
            // }
            // }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return true;
    }

    public boolean checkLogin(String username, String password) throws IOException {
        ArrayList<String> loginCreds = readLoginCredentials();
        boolean loginValid = false;
        for (int i = 0; i < loginCreds.size(); i++) {
            if (loginCreds.get(i).equals(username) && loginCreds.get(i + 1).equals(password)) {
                loginValid = true;
                break;
            } else {
                loginValid = false;
            }
        }
        if (loginValid) {
            System.out.println("Welcome, " + username + ".");
        } else {
            System.out.println("Invalid username or password.");
            System.out.print("Enter username: ");
            username = input.next();
            System.out.print("Enter password: ");
            password = input.next();
            checkLogin(username, password);
        }
        return true;
    }

    public static int checkUserType(String username) throws IOException {
        ArrayList<String> userTypes = readUserTypes();
        int userType = 0;
        for (int i = 0; i < userTypes.size(); i++) {
            if (userTypes.get(i).equals(username)) {
                userType = Integer.parseInt(userTypes.get(i + 1));
                break;
            }
        }
        return userType;
    }

    private static ArrayList<String> readUserTypes() throws IOException {
        ArrayList<String> userTypes = new ArrayList<>();
        // read donors.csv into a list of lines.
        List<String> donors = Files.readAllLines(Paths.get("userdata/donors.csv"));
        for (int i = 1; i < donors.size(); i++) { // init at 1 to ignore column title
            String[] items = donors.get(i).split(","); // split a line by comma
            userTypes.add(items[0]); // username
            userTypes.add(items[4]); // usertype (1 = NGO, 0 = Donor)
        }
        // read ngos.csv into a list of lines.
        List<String> ngos = Files.readAllLines(Paths.get("userdata/ngos.csv"));
        for (int i = 1; i < ngos.size(); i++) {
            String[] items = ngos.get(i).split(",");
            userTypes.add(items[0]);
            userTypes.add(items[4]);
        }
        return userTypes;
    }

    private static ArrayList<String> readLoginCredentials() throws IOException {
        ArrayList<String> loginCreds = new ArrayList<>();
        // read donors.csv into a list of lines.
        List<String> donors = Files.readAllLines(Paths.get("userdata/donors.csv"));
        for (int i = 1; i < donors.size(); i++) { // init at 1 to ignore column title
            String[] items = donors.get(i).split(","); // split line by comma
            loginCreds.add(items[0]); // username
            loginCreds.add(items[1]); // password
        }
        // read ngos.csv into a list of lines.
        List<String> ngos = Files.readAllLines(Paths.get("userdata/ngos.csv"));
        for (int i = 1; i < ngos.size(); i++) {
            String[] items = ngos.get(i).split(",");
            loginCreds.add(items[0]);
            loginCreds.add(items[1]);
        }
        return loginCreds;
    }

    private void createAccount() {
        AidSystem.displayHeader("New User Registration");
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

    private void donorRegister() {
        try {
            Donor d = new Donor();
            OutputStream output = new BufferedOutputStream(new FileOutputStream("userdata/donors.csv", true));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            AidSystem.displayHeader("Login Credentials");
            System.out.print("Enter a username: ");
            String username = input.next();
            checkUsername(username, d);
            System.out.print("Enter a password: ");
            d.setPassword(input.next());
            AidSystem.displayHeader("Personal Details");
            System.out.print("Enter your name: ");
            String name = input.next();
            d.setName(name);
            System.out.print("Enter your phone number (E.g. 012-3456789): ");
            String phone = input.next();
            checkPhone(phone, d);
            writer.write(d.getUsername() + "," + d.getPassword() + "," + d.getName() + "," + d.getPhone() + ",0");
            writer.newLine();
            System.out.println("New Donor account successfully created. Happy Donating!");
            System.out.println("Please login with your new account to continue using the app.");
            writer.close();
            output.close();
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    private void ngoRegister() {
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
            writer.write(n.getUsername() + "," + n.getPassword() + "," + n.getNGO() + "," + n.getManpower() + ",1");
            writer.newLine();
            System.out.println("New NGO account successfully created. You can start requesting!");
            System.out.println("Please login with your new account to continue using the app.");
            writer.close();
            output.close();
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    private boolean phoneValid(String phone) {
        Pattern p = Pattern.compile("^01\\d-\\d{7,8}$"); // No. format must be 01x-xxxxxxxx
        Matcher m = p.matcher(phone);
        return (m.matches()); // returns boolean value, true means accepted.
    }

    private void checkPhone(String phonenum, Donor d) {
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
            System.out.println("Username is good to go!");
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