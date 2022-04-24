package src;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Donor is an account type for donors. Additional data fields include Name and
 * Phone Number.
 */
public class Donor extends Account {
    public String name;
    public String phone;

    /**
     * No-arg constructor
     */
    public Donor() {
    }

    /**
     * Constructs a new Donor object that has a username, password, name and phone
     * number.
     * 
     * @param username Donor's username
     * @param password Donor's password
     * @param name     Donor's name
     * @param phone    Donor's phone number
     */
    public Donor(String username, String password, String name, String phone) {
        super(username, password);
        this.name = name;
        this.phone = phone;
    }

    /**
     * Sets name for the donor.
     * 
     * @param name Donor name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets phone number for the donor.
     * 
     * @param phone Donor's phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets donor name.
     * 
     * @return Returns donor name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets donor phone number.
     * 
     * @return Return donor's phone number.
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Main menu for Donor. Shows all available options for the Donor to do.
     * 
     * @param donor             The current Donor user logged in
     * @param requestList       List of current requests made by NGOs
     * @param aidsCompletedList List of all donations made
     * @param input             Scanner input from main is passed as argument
     */
    public static void menu(Donor donor, ArrayList<Request> requestList, ArrayList<AidsCompleted> aidsCompletedList,
            Scanner input) {
        int choice;
        do {
            AidSystem.displayHeader("Donor Main Menu");
            System.out.println("Hello, " + donor.getName() + "!");
            System.out.println("Select an option:");
            System.out.println("1. Make a Donation");
            System.out.println("2. View My Donations");
            System.out.println("3. View NGO Aid Requests");
            System.out.println("4. View All Donations");
            System.out.println("5. Exit Program");
            System.out.print("Enter choice --> ");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    donatingForm(donor, requestList, aidsCompletedList, input);
                    break;
                case 2:
                    viewPersonalDonations(donor, aidsCompletedList, input);
                    break;
                case 3:
                    viewNGOrequests(requestList);
                    break;
                case 4:
                    viewAllDonations(aidsCompletedList);
                    break;
                case 5:
                    System.out.println("Exiting the program...\n Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input. Please enter an integer within 1 to 5.");
            }
        } while (choice != 5);
        input.close();
    }

    /**
     * Donating form for donors to fill in to donate an item of their choice from
     * list of available items.
     * 
     * @param donor             Currently logged in Donor user
     * @param requestList       List of current requests made by NGOs
     * @param aidsCompletedList List of all donations made
     * @param input             Scanner input from main is passed as argument
     */
    public static void donatingForm(Donor donor, ArrayList<Request> requestList,
            ArrayList<AidsCompleted> aidsCompletedList, Scanner input) {
        try {
            ArrayList<String> itemData = new ArrayList<>();
            Aid aid = new Aid();
            int qty = 0;
            String donate;

            itemData.add("rice");
            itemData.add("flour");
            itemData.add("sugar");
            itemData.add("sardine");
            itemData.add("biscuit");

            AidSystem.displayHeader("Donating Form");
            System.out.println("\nList of acceptable donations:\n - Rice\n - Flour\n - Sugar\n - Sardine\n - Biscuit");
            System.out.print("\nEnter an item you wish to donate --> ");
            donate = input.next().toLowerCase();

            if (itemData.contains(donate)) {
                System.out.println("\nItem is needed. What is the quantity of your donation?");
                System.out.print("Quantity --> ");
                try {
                    qty = input.nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("Invalid input. Please enter an integer.");
                }
                Aid userAid = new Aid(donor.getUsername(), donor.getPhone(), donate, qty);
                Aid.matchDonation(userAid, requestList, aidsCompletedList);
                if (userAid.getQuantity() != 0) {
                    aidsCompletedList.add(new AidsCompleted(userAid.getName(), userAid.getPhone(), userAid.getAid(),
                            userAid.setNull(), userAid.getQuantity(),
                            userAid.setNull(), aid.setAvailable()));
                }
                Request.writeRequestFile(requestList);
                AidsCompleted.writeAidsCompletedFile(aidsCompletedList);

                System.out.println("\nItem donation was successful. Thank you!");

            } else {
                System.out.println("\nWe currently do not accept the specified item. Please enter another item.");
                donatingForm(donor, requestList, aidsCompletedList, input);
                System.out.println();
            }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    /**
     * Lists out all donations made by current Donor user.
     * 
     * @param donor             Current Donor user logged in
     * @param aidsCompletedList List of all donations made
     * @param input             Scanner input from main
     */
    private static void viewPersonalDonations(Donor donor, ArrayList<AidsCompleted> aidsCompletedList, Scanner input) {
        AidSystem.displayHeader("My Donations");
        System.out.println("\nHere are your donations so far, " + donor.getName() + "!");
        System.out.printf("%10s\t%12s\t%10s\t%8s\t%7s\t%8s\t%10s",
                "Name", "Phone", "Item", "Quantity", "To NGO", "Manpower", "Status\n");
        for (AidsCompleted s : aidsCompletedList) {
            if (donor.getUsername().equals(s.getDonor())) {
                System.out.println(s);
            }
        }
    }

    /**
     * Lists down all current requests made by all NGOs.
     * 
     * @param requestList List of requests made by NGOs read from requests.csv
     */
    private static void viewNGOrequests(ArrayList<Request> requestList) {
        AidSystem.displayHeader("Requests by NGOs");
        System.out.printf("%5s\t%8s\t%10s\t%8s", "NGO", "Manpower", "Aid", "Quantity\n");
        for (Request s : requestList) {
            System.out.println(s);
        }
    }
}