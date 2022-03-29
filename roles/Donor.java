// PIC: MOHD FATIH FIKRI BIN MASIARA (1201302166)
// Donor role
package roles;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.InputMismatchException;

public class Donor extends Account {
    public String name;
    public String phone;

    public Donor() {
    }

    public Donor(String username, String password, String name, String phone) {
        super(username, password);
        this.name = name;
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

    public static void menu(int choice, Donor donor) {

        displayHeader("Donor Main Menu");
        System.out.println("Hello, " + donor.getName() + "!");
        System.out.println("Select an option:");
        System.out.println("1. Make a Donation!");
        System.out.println("2. View My Donations");
        System.out.println("3. View NGO Aid Requests");
        System.out.println("4. View All Donations");
        System.out.println("5. Exit Program");
        choice = getMenuInput();
        System.out.println(choice);
        donorActions(choice, donor);

    }

    private static void donorActions(int choice, Donor donor) {
        switch (choice) {
            case 1:
                donatingForm(donor);
                break;
            case 2:
                viewPersonalDonations(donor);
                break;
            case 3:
                viewNGOrequests();
                System.out.print(choice);
                break;
            case 4:
                viewAllDonations();
                break;
            case 5:
                System.out.println("Exiting the program...");
                System.out.println("Goodbye!");
                System.exit(0);
                break;
        }
    }

    private static void donatingForm(Donor donor) {

        System.out.println("Let's make a donation.");
        try {
            Scanner keyboard = new Scanner(System.in);
            ArrayList<String> itemData = new ArrayList<>();
            Aid aid = new Aid();
            int qty = 0;
            boolean isExist = false;
            String donate;

            itemData.add("apple");
            itemData.add("lemon");
            itemData.add("orange");
            itemData.add("babystuff");
            itemData.add("toileteries");
            itemData.add("towel");
            itemData.add("blanket");

            System.out.println();
            System.out.println("Enter ONE Item That You Want To Donate? :");
            donate = keyboard.next();

            try {
                System.out.println("Enter Quantity: ");
                qty = keyboard.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input. Please enter an integer.");
            }

            if (isExist == itemData.contains(donate)) {
                System.out.println("Item is neded,Donation Item successfully added. Thank you!");

                aid.setAid(donate);
                aid.setQuantity(qty);
            } else
                System.out.println("We dont need the item,sorry,please enter another item");
            donatingForm(donor);

            System.out.println();

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
        System.out.println();
        boolean programRun = true;
        do {
            try {
                System.out.println("Do You Want To Donate Another Item? :");
                System.out.println("(1) Yes");
                System.out.println("(2) No");

                int choice = keyboard.nextInt();
                while (choice != 1 && choice != 2) {
                    System.out.println("Invalid input. Please enter 1 or 2.");
                    choice = keyboard.nextInt();
                }
                if (choice == 1) {
                    donatingForm(donor);
                    programRun = false;
                } else if (choice == 2) {
                    menu(choice, donor);
                    programRun = false;
                }
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input. Please enter an integer.");
                keyboard.next();
            }
        } while (programRun == true);
        keyboard.close();
    }

    private static void viewPersonalDonations(Donor donor) {
        System.out.println("What have I donated?");
        System.out.println(donor.getName());

    }

    private static void viewNGOrequests() {
        System.out.println("What are NGOs requesting?");
    }

}