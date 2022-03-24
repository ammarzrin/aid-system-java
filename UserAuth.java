// Main program is run here.

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

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
        System.out.println("Register page is displayed.");
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
            OutputStream output = new BufferedOutputStream(new FileOutputStream("userdata/donors.csv"));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            System.out.println("--- Login Credentials --- ");
            System.out.print("Enter a username: ");
            d.setUsername(input.next());
            System.out.print("Enter a password: ");
            d.setPassword(input.next());
            System.out.println("--- Personal Details --- ");
            System.out.print("Enter your name: ");
            d.setName(input.next());
            System.out.print("Enter your phone number: ");
            d.setPhone(input.next());

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
            OutputStream output = new BufferedOutputStream(new FileOutputStream("userdata/ngos.csv"));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            System.out.println("--- Login Credentials --- ");
            System.out.print("Enter a username: ");
            n.setUsername(input.next());
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
}