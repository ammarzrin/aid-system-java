package src;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NGO extends Account {
    public String ngoname;
    public int manpower;

    public NGO() {
    }

    public NGO(String username, String password, String ngoname, int manpower) {
        super(username, password);
        this.ngoname = ngoname;
        this.manpower = manpower;
    }

    public void setNGO(String ngoname) {
        this.ngoname = ngoname;
    }

    public void setManpower(int manpower) {
        this.manpower = manpower;
    }

    public String getNGO() {
        return this.ngoname;
    }

    public int getManpower() {
        return this.manpower;
    }

    public static void menu(NGO ngo, ArrayList<Request> requestList, ArrayList<AidsCompleted> aidsCompletedList,
            Scanner input) {

        int choice;

        do {
            AidSystem.displayHeader("NGO Main Menu");
            System.out.println("Hello, " + ngo.getNGO() + "!");
            System.out.println("Select an option:");
            System.out.println("1. Make a Request!");
            System.out.println("2. View My Requests");
            System.out.println("3. View Received Donations");
            System.out.println("4. View All Donations");
            System.out.println("5. Exit Program");

            choice = input.nextInt();

            switch (choice) {
                case 1:
                    requestingForm(ngo, requestList, aidsCompletedList, input);
                    break;
                case 2:
                    viewMyRequests(ngo, requestList, aidsCompletedList, input);
                    break;
                case 3:
                    viewReceivedAids(ngo, requestList, aidsCompletedList, input);
                    break;
                case 4:
                    viewAllDonations(aidsCompletedList);
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

    private static void requestingForm(NGO ngo, ArrayList<Request> requestList,
            ArrayList<AidsCompleted> aidsCompletedList, Scanner input) {
        System.out.println("Let's make a request.");
        try {
            ArrayList<String> itemData = new ArrayList<>();
            int qty = 0;

            String request;

            itemData.add("apple");
            itemData.add("lemon");
            itemData.add("orange");
            itemData.add("rice");
            itemData.add("watermelon");

            System.out.println();
            System.out.println("Enter ONE Item That You Want To Donate? :");

            request = input.next();

            try {
                System.out.println("Enter Quantity: ");
                qty = input.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input. Please enter an integer.");
            }

            if (itemData.contains(request)) {
                System.out.println("Item is needed,Donation Item successfully added. Thank you!");
                Request userRequest = new Request(ngo.getNGO(), ngo.getManpower(), request, qty);

                Request.matchRequest(userRequest, requestList, aidsCompletedList);

                if (userRequest.getQuantity() != 0) {
                    requestList.add(userRequest);
                }

                Request.writeRequestFile(requestList);
                AidsCompleted.writeAidsCompletedFile(aidsCompletedList);

                for (Request s : requestList) {
                    System.out.println(s);
                }
                for (AidsCompleted s : aidsCompletedList) {
                    System.out.println(s);
                }

            }

            else {
                System.out.println("We dont need the item,sorry,please enter another item");
                requestingForm(ngo, requestList, aidsCompletedList, input);
                System.out.println();
            }

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    private static void viewMyRequests(NGO ngo, ArrayList<Request> requestList,
            ArrayList<AidsCompleted> aidsCompletedList, Scanner input) {
        System.out.println("What have I requested?");

        ArrayList<String> itemData = new ArrayList<>();

        itemData.add("apple");
        itemData.add("lemon");
        itemData.add("orange");
        itemData.add("rice");
        itemData.add("watermelon");

        System.out.println("    aid    " + "    ngo    "
                + "    quantity    " + "    manpower    ");

        System.out.println("which item to do you want to see?");
        String aidQuery = input.next();

        if (itemData.contains(aidQuery)) {
            System.out.println("    aid    " + "    ngo    "
                    + "    quantity    " + "    manpower    ");

            for (Request s : requestList) {
                if (ngo.getNGO().equals(s.getNGO()) && aidQuery.equals(s.getAid())) {
                    System.out.println(s);
                }
            }
        }
        input.close();
    }

    private static void viewReceivedAids(NGO ngo, ArrayList<Request> requestList,
            ArrayList<AidsCompleted> aidsCompletedList, Scanner input) {
        System.out.println("What donations have my NGO received?");
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
                if (ngo.getNGO().equals(s.getngoName()) && aidQuery.equals(s.getAid())) {
                    System.out.println(s);
                }
            }
        }
        input.close();
    }
}
