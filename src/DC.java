package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class TestDC {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        int choice;
        ArrayList<String> itemData = new ArrayList<>();

        itemData.add("apple");
        itemData.add("lemon");
        itemData.add("orange");
        itemData.add("rice");
        itemData.add("watermelon");

        ArrayList<AidsCompleted> aidsCompletedList = AidsCompleted.readAidsCompletedFile();

        do {

            displayHeader("DC Main Menu");

            System.out.println("Select an option:");
            System.out.println("1. Match donation one to one");
            System.out.println("2. Match donations one to many ");
            System.out.println("3. Match donations many to one");
            System.out.println("4. Match donations many to many");
            System.out.println("5. Queue donations");
            System.out.println("6. Exit Program");

            // choice = getMenuInput();
            choice = input.nextInt();
            DC.dcActions(choice, aidsCompletedList, input, itemData);

        } while (choice != 6);
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
}

public class DC {
    public static void dcActions(int choice, ArrayList<AidsCompleted> aidsCompletedList, Scanner input,
            ArrayList<String> ItemData) {
        String aid;
        String Donor;
        String Ngo;

        switch (choice) {
            case 1:
                aid = MatchQueryAid(ItemData, input);
                Donor = MatchQueryDonor(input);
                Ngo = MatchQueryNgo(input);

                MatchOneOne(aid, Donor, Ngo, aidsCompletedList);
                break;
            case 2:
                aid = MatchQueryAid(ItemData, input);
                Donor = MatchQueryDonor(input);

                MatchOneMany(aid, Donor, aidsCompletedList);
                break;
            case 3:
                aid = MatchQueryAid(ItemData, input);
                Ngo = MatchQueryNgo(input);

                MatchManyOne(aid, Ngo, aidsCompletedList);
                break;
            case 4:
                aid = MatchQueryAid(ItemData, input);

                MatchManyMany(aid, aidsCompletedList);
                break;
            case 5:
                queueDonation(input, aidsCompletedList);
                break;
            case 6:
                System.out.println("Exiting the program...");
                System.out.println("Goodbye!");
                System.exit(0);
                break;
        }
    }

    public static String MatchQueryAid(ArrayList<String> ItemData, Scanner input) {

        System.out.println("Please enter the aid you want to match");
        String aid = input.next();
        // if (itemData.contains(aid))
        return aid;
    }

    public static String MatchQueryDonor(Scanner input) {
        System.out.println("Please enter the Donor you want to match with");
        String Donor = input.next();

        return Donor;
    }

    public static String MatchQueryNgo(Scanner input) {
        System.out.println("Please enter the NGO you want to match with");
        String Ngo = input.next();

        return Ngo;
    }

    public static void MatchOneOne(String aid, String Donor, String Ngo, ArrayList<AidsCompleted> aidsCompletedList) {

        for (AidsCompleted s : aidsCompletedList) {
            if (s.getDonor().equals(Donor) && s.getAid().equals(aid) && s.getngoName().equals(Ngo)) {
                System.out.println(s);
            }
        }
    }

    public static void MatchOneMany(String aid, String Donor, ArrayList<AidsCompleted> aidsCompletedList) {

        for (AidsCompleted s : aidsCompletedList) {
            if (s.getDonor().equals(Donor) && s.getAid().equals(aid)) {
                System.out.println(s);
            }
        }
    }

    public static void MatchManyOne(String aid, String Ngo, ArrayList<AidsCompleted> aidsCompletedList) {
        for (AidsCompleted s : aidsCompletedList) {
            if (s.getAid().equals(aid) && s.getngoName().equals(Ngo)) {
                System.out.println(s);
            }
        }
    }

    public static void MatchManyMany(String aid, ArrayList<AidsCompleted> aidsCompletedList) {
        for (AidsCompleted s : aidsCompletedList) {
            if (s.getAid().equals(aid)) {
                System.out.println(s);
            }
        }
    }

    public static void queueDonation(Scanner input, ArrayList<AidsCompleted> aidsCompletedList) {
        int choice;
        do {

            TestDC.displayHeader("Queue Donation");

            System.out.println("Select an option:");
            System.out.println("1. FIFO Queue");
            System.out.println("2. Priority Queue");
            System.out.println("3. Back");

            // choice = getMenuInput();
            choice = input.nextInt();
            queueDonationActions(choice, aidsCompletedList, input);

        } while (choice != 3);
    }

    public static void queueDonationActions(int choice, ArrayList<AidsCompleted> aidsCompletedList, Scanner input) {

        switch (choice) {
            case 1:
                queueFIFO(aidsCompletedList, input);
                break;
            case 2:
                queuePriority(aidsCompletedList, input);
                break;
            case 3:
                break;
        }
    }

    public static void queueFIFO(ArrayList<AidsCompleted> aidsCompletedList, Scanner input) {
        System.out.print("hi");
    }

    public static void queuePriority(ArrayList<AidsCompleted> aidsCompletedList, Scanner input) {
        System.out.print("hello");
    }
}
