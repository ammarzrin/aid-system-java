package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAuth {

    static Scanner input = new Scanner(System.in);

    public UserAuth(Donor donor, NGO ngo, int type) {
        type = welcomePage(donor, ngo, type);
    }

    public static int welcomePage(Donor donor, NGO ngo, int type) {
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
                    type = loginAccount(donor, ngo, type);
                    loggedIn = true;
                } else if (choice == 2) {
                    createAccount();
                    loggedIn = false;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input. Please enter an integer.");
                input.next();
            }
        } while (!loggedIn);
        return type;
    }

    public static int loginAccount(Donor donor, NGO ngo, int type) {
        AidSystem.displayHeader("User Login");
        String username, password;
        try {
            System.out.print("Enter username: ");
            username = input.next();
            System.out.print("Enter password: ");
            password = input.next();
            type = checkLogin(username, password, donor, ngo, type);
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        return type;
    }

    public static int checkLogin(String username, String password, Donor donor, NGO ngo, int type) throws IOException {
        ArrayList<String> userData = readUserData();
        System.out.print(userData);
        boolean loginValid = false;
        do {
            for (int i = 0; i < userData.size(); i++) {
                if (userData.get(i).equals(username) && userData.get(i + 1).equals(password)) {
                    if (userData.get(i).equals(username) && userData.get(i + 2).equals("0")) {
                        donor.setUsername(username);
                        donor.setPassword(password);
                        donor.setName(userData.get(i + 3));
                        donor.setPhone(userData.get(i + 4));
                        type = 0;
                    } else if (userData.get(i).equals(username) && userData.get(i + 2).equals("1")) {
                        ngo.setUsername(username);
                        ngo.setPassword(password);
                        ngo.setNGO(userData.get(i + 3));
                        ngo.setManpower(Integer.parseInt(userData.get(i + 4)));
                        type = 1;
                    }
                    loginValid = true;
                    break;
                } else if (!userData.get(i).equals(username) && !userData.get(i + 1).equals(password)) {
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
            }
        } while (!loginValid);
        return type;
    }

    private static ArrayList<String> readUserData() throws IOException {
        ArrayList<String> userData = new ArrayList<>();
        // read users.csv into a list of lines.
        List<String> users = Files.readAllLines(Paths.get("src/users.csv"));
        for (int i = 1; i < users.size(); i++) { // init at 1 to ignore column title
            String[] items = users.get(i).split(","); // split line by comma
            userData.add(items[0]); // username
            userData.add(items[1]); // password
            userData.add(items[2]); // usertype donor(0) OR ngo(1)
            userData.add(items[3]); // name/ngo
            userData.add(items[4]); // phone/manpower
        }
        return userData;
    }

    private static void createAccount() {
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

    private static void donorRegister() {
        try {
            AidSystem.displayHeader("Login Credentials");
            System.out.print("Enter a username: ");
            String username = input.next();
            checkUsername(username);
            System.out.print("Enter a password: ");
            String password = input.next();
            AidSystem.displayHeader("Personal Details");
            System.out.print("Enter your name: ");
            String name = input.next();
            System.out.print("Enter your phone number (E.g. 012-3456789): ");
            String phone = input.next();
            checkPhone(phone);

            StringBuilder sb = new StringBuilder();
            sb.append(username + "," + password + "," + 0 + "," + name + "," + phone + "\n");
            Files.write(Paths.get("src/users.csv"), sb.toString().getBytes(), StandardOpenOption.APPEND);

            System.out.println("New Donor account successfully created. Happy Donating!");
            System.out.println("Please login with your new account to continue using the app.");
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    private static void ngoRegister() {
        try {
            AidSystem.displayHeader("Login Credentials");
            System.out.print("Enter a username: ");
            String username = input.next();
            checkUsername(username);
            System.out.print("Enter a password: ");
            String password = input.next();
            AidSystem.displayHeader("Personal Details");
            System.out.print("Enter your NGO's initials: ");
            String ngoname = input.next();
            System.out.print("Enter your NGO's manpower number: ");
            int manpower = input.nextInt();

            StringBuilder sb = new StringBuilder();
            sb.append(username + "," + password + "," + 1 + "," + ngoname + "," + manpower + "\n");
            Files.write(Paths.get("src/users.csv"), sb.toString().getBytes(), StandardOpenOption.APPEND);

            System.out.println("New NGO account successfully created. You can start requesting!");
            System.out.println("Please login with your new account to continue using the app.");
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    private static boolean phoneValid(String phone) {
        Pattern p = Pattern.compile("^01\\d-\\d{7,8}$"); // No. format must be 01x-xxxxxxxx
        Matcher m = p.matcher(phone);
        return (m.matches()); // returns boolean value, true means accepted.
    }

    private static void checkPhone(String phonenum) {
        if (phoneValid(phonenum)) {
            System.out.println("Phone number is good to go!");
        } else {
            System.out.println("Invalid phone number!");
            System.out.print("Re-enter your phone number (E.g. 012-3456789): ");
            checkPhone(input.next());
        }
    }

    private static void checkUsername(String username) throws IOException {
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
            checkUsername(input.next());
        } else {
            System.out.println("Username is good to go!");
        }
    }

    private static ArrayList<String> readUsernames() throws IOException {
        ArrayList<String> usernames = new ArrayList<>();
        // read users.csv into a list of lines.
        List<String> users = Files.readAllLines(Paths.get("src/users.csv"));
        // init at 1 to ignore column name
        for (int i = 1; i < users.size(); i++) {
            // split a line by comma
            String[] items = users.get(i).split(",");
            // users[0] is username
            usernames.add(items[0]);
        }
        return usernames;
    }
}