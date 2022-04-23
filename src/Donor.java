package src;

// PIC: MOHD FATIH FIKRI BIN MASIARA (1201302166)
// Donor role
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

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

    public static void menu(Donor donor, ArrayList<Request> requestList, ArrayList<AidsCompleted> aidsCompletedList,
            Scanner input) {
        int choice;
        do {
            displayHeader("Donor Main Menu");
            System.out.println("Hello, " + donor.getName() + "!");
            System.out.println("Select an option:");
            System.out.println("1. Make a Donation!");
            System.out.println("2. View My Donations");
            System.out.println("3. View NGO Aid Requests");
            System.out.println("4. View All Donations");
            System.out.println("5. Exit Program");

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
        } while (choice != 5);
        input.close();
    }

    public static void donatingForm(Donor donor, ArrayList<Request> requestList,
            ArrayList<AidsCompleted> aidsCompletedList, Scanner input) {

        System.out.println("Let's make a donation.");
        try {
            ArrayList<String> itemData = new ArrayList<>();
            Aid aid = new Aid();
            int qty = 0;
            String donate;

            itemData.add("apple");
            itemData.add("lemon");
            itemData.add("orange");
            itemData.add("rice");
            itemData.add("watermelon");

            System.out.println();
            System.out.println("Enter ONE Item That You Want To Donate? :");

            donate = input.next();

            try {
                System.out.println("Enter Quantity: ");
                qty = input.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input. Please enter an integer.");
            }

            if (itemData.contains(donate)) {
                System.out.println("Item is neded,Donation Item successfully added. Thank you!");
                Aid userAid = new Aid(donor.getUsername(), donor.getPhone(), donate, qty);

                Aid.matchDonation(userAid, requestList, aidsCompletedList);

                if (userAid.getQuantity() != 0) {

                    aidsCompletedList.add(new AidsCompleted(userAid.getName(), userAid.getPhone(), userAid.getAid(),
                            userAid.setNull(), userAid.getQuantity(),
                            userAid.setNull(), aid.setAvailable()));
                }

                Request.writeRequestFile(requestList);
                AidsCompleted.writeAidsCompletedFile(aidsCompletedList);
            }

            else {
                System.out.println("We dont need the item,sorry,please enter another item");
                donatingForm(donor, requestList, aidsCompletedList, input);

                System.out.println();
            }

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    private static void viewPersonalDonations(Donor donor, ArrayList<AidsCompleted> aidsCompletedList, Scanner input) {
        System.out.println("What have I donated?");
        ArrayList<String> itemData = new ArrayList<>();

        itemData.add("apple");
        itemData.add("lemon");
        itemData.add("orange");
        itemData.add("rice");
        itemData.add("watermelon");

        System.out.println("which item to do you want to see?");
        String aidQuery = input.next();

        if (itemData.contains(aidQuery)) {
            System.out.println("    donor    " + "    Phone    " + "    aid    " + "    ngo    "
                    + "    quantity    " + "    manpower    ");
            for (AidsCompleted s : aidsCompletedList) {
                if (donor.getUsername().equals(s.getDonor()) && aidQuery.equals(s.getAid())) {
                    System.out.println(s);
                }
            }
        }
    }

    private static void viewNGOrequests(ArrayList<Request> requestList) {
        System.out.println("What are NGOs requesting?");
        for (Request s : requestList) {
            System.out.println(s);
        }
    }
}