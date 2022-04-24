package src;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * NGO is an account type for NGOs. Additional data fields include NGO initials
 * and NGO manpower number.
 */
public class NGO extends Account {
    public String ngoname;
    public int manpower;

    /**
     * No-arg constructor
     */
    public NGO() {
    }

    /**
     * Constructs a new NGO object that has a username, password, ngo initials and
     * manpower.
     * 
     * @param username
     * @param password
     * @param ngoname
     * @param manpower
     */
    public NGO(String username, String password, String ngoname, int manpower) {
        super(username, password);
        this.ngoname = ngoname;
        this.manpower = manpower;
    }

    /**
     * Sets the NGO initials for the NGO representative.
     * 
     * @param ngoname NGO name
     */
    public void setNGO(String ngoname) {
        this.ngoname = ngoname;
    }

    /**
     * Sets the NGO manpower number.
     * 
     * @param manpower NGO manpower number
     */
    public void setManpower(int manpower) {
        this.manpower = manpower;
    }

    /**
     * Gets NGO name.
     */
    public String getNGO() {
        return this.ngoname;
    }

    /**
     * Gets manpower number for NGO.
     * 
     * @return Returns the NGO's manpower
     */
    public int getManpower() {
        return this.manpower;
    }

    /**
     * Main menu for NGO representative. Shows all available options for the NGO to
     * do.
     * 
     * @param ngo               The current NGO user logged in
     * @param requestList       List of current requests made by NGOs
     * @param aidsCompletedList List of all donations made
     * @param input             Scanner input from main
     */
    public static void menu(NGO ngo, ArrayList<Request> requestList, ArrayList<AidsCompleted> aidsCompletedList,
            Scanner input) {
        int choice;
        do {
            AidSystem.displayHeader("NGO Main Menu");
            System.out.println("Hello, " + ngo.getNGO() + "!");
            System.out.println("Select an option:");
            System.out.println("1. Make a Request!");
            System.out.println("2. View My Requests");
            System.out.println("3. View Reserved Donations");
            System.out.println("4. View All Donations");
            System.out.println("5. Exit Program");
            System.out.print("Enter choice --> ");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    requestingForm(ngo, requestList, aidsCompletedList, input);
                    break;
                case 2:
                    viewMyRequests(ngo, requestList, aidsCompletedList, input);
                    break;
                case 3:
                    viewReservedAids(ngo, requestList, aidsCompletedList, input);
                    break;
                case 4:
                    viewAllDonations(aidsCompletedList);
                    break;
                case 5:
                    System.out.println("Exiting the program...\nGoodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid input. Please enter an integer within 1 to 5.");
            }
        } while (choice != 5);
        input.close();
    }

    /**
     * Requesting form for the NGO to request for specific item and quantity so
     * Donors can see what to donate.
     * 
     * @param ngo               Currently logged in NGO user
     * @param requestList       List of current requests made by NGOs
     * @param aidsCompletedList List of all donations made
     * @param input             Scanner input from main
     */
    private static void requestingForm(NGO ngo, ArrayList<Request> requestList,
            ArrayList<AidsCompleted> aidsCompletedList, Scanner input) {
        try {
            ArrayList<String> itemData = new ArrayList<>();
            int qty = 0;
            String request;

            itemData.add("rice");
            itemData.add("flour");
            itemData.add("sugar");
            itemData.add("sardine");
            itemData.add("biscuit");

            AidSystem.displayHeader("Requesting Form");
            System.out.println("\nList of acceptable donations:\n - Rice\n - Flour\n - Sugar\n - Sardine\n - Biscuit");
            System.out.print("Enter an item you wish to request --> ");
            request = input.next().toLowerCase();

            if (itemData.contains(request)) {
                try {
                    System.out.print("Enter quantity -->  ");
                    qty = input.nextInt();
                } catch (InputMismatchException ex) {
                    System.out.println("Invalid input. Please enter an integer.");
                }
                Request userRequest = new Request(ngo.getNGO(), ngo.getManpower(), request, qty);
                System.out.println("Your request have been made successfully.");
                Request.matchRequest(userRequest, requestList, aidsCompletedList);
                if (userRequest.getQuantity() != 0) {
                    requestList.add(userRequest);
                }
                Request.writeRequestFile(requestList);
                AidsCompleted.writeAidsCompletedFile(aidsCompletedList);
            } else {
                System.out.println("\nWe currently do not accept the item at our DC. Please enter another item.");
                requestingForm(ngo, requestList, aidsCompletedList, input);
                System.out.println();
            }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    /**
     * Lists out all requests made by the currently logged in NGO.
     * 
     * @param ngo               Current NGO logged in
     * @param requestList       List of requests made by NGOs
     * @param aidsCompletedList List of all donations made
     * @param input             Scanner input from main
     */
    private static void viewMyRequests(NGO ngo, ArrayList<Request> requestList,
            ArrayList<AidsCompleted> aidsCompletedList, Scanner input) {
        AidSystem.displayHeader("Our Requests");
        System.out.println("Here are your requests so far, " + ngo.getNGO() + "!");
        System.out.printf("%5s\t%8s\t%10s\t%8s", "NGO", "Manpower", "Aid", "Quantity\n");
        for (Request s : requestList) {
            if (ngo.getNGO().equals(s.getNGO())) {
                System.out.println(s);
            }
        }
    }

    /**
     * Lists down all received donations to the logged in NGO.
     * 
     * @param ngo               Current NGO user logged in
     * @param requestList       List of requests made by NGOs
     * @param aidsCompletedList List of all donations made
     * @param input             Scanner input from main
     */
    private static void viewReservedAids(NGO ngo, ArrayList<Request> requestList,
            ArrayList<AidsCompleted> aidsCompletedList, Scanner input) {
        AidSystem.displayHeader("Reserved Donations");
        System.out.println("Here are the donations reserved to your NGO!");
        System.out.printf("%10s\t%12s\t%10s\t%8s\t%7s\t%8s\t%10s",
                "Name", "Phone", "Item", "Quantity", "To NGO", "Manpower", "Status\n");
        for (AidsCompleted s : aidsCompletedList) {
            if (ngo.getNGO().equals(s.getngoName())) {
                System.out.println(s);
            }
        }
    }
}