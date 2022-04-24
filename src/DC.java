package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

/**
 * Main function is stored here. Start the program here to demonstrate
 * collection system.
 */
class TestDC {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        int choice;
        ArrayList<String> itemData = new ArrayList<>();

        itemData.add("rice");
        itemData.add("flour");
        itemData.add("sugar");
        itemData.add("sardine");
        itemData.add("biscuit");

        ArrayList<AidsCompleted> aidsCompletedList = AidsCompleted.readAidsCompletedFile();

        do {
            AidSystem.displayHeader("DC Main Menu");
            System.out.println("\nList of acceptable donations:\n - Rice\n - Flour\n - Sugar\n - Sardine\n - Biscuit");
            System.out.println("Select an option:");
            System.out.println("1. Match donations one to one");
            System.out.println("2. Match donations one to many ");
            System.out.println("3. Match donations many to one");
            System.out.println("4. Match donations many to many");
            System.out.println("5. Queue donations");
            System.out.println("6. Exit Program");

            choice = input.nextInt();
            DC.dcActions(choice, aidsCompletedList, input, itemData);

        } while (choice != 6);
    }
}

/**
 * This class is the comparator implementation for AidPriority class.
 */
class AidComparator implements Comparator<AidPriority> {

    /**
     * Compares manpower number between two NGOs for PriorityQueue implementation
     * for Collection system.
     */
    public int compare(AidPriority a1, AidPriority a2) {

        if (a1.manpower < a2.manpower)
            return 1;
        else if (a1.manpower > a2.manpower)
            return -1;
        else
            return 0;
    }
}

/**
 * This class is a constructor for the queue system.
 */
class AidPriority {
    public String ngo;
    public int manpower;

    // A constructor for aid priority
    public AidPriority(String ngo, int manpower) {
        this.ngo = ngo;
        this.manpower = manpower;
    }

    /**
     * Gets NGO name.
     * 
     * @return Returns NGO's initials.
     */
    public String getNgo() {
        return ngo;
    }

    /**
     * Gets manpower number of the NGO.
     * 
     * @return Returns NGO's manpower number.
     */
    public int getManpower() {
        return manpower;
    }

}

/**
 * Contains everything in DC including the menu, actions and methods for both
 * FIFO queue and PriorityQueue
 */
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

            AidSystem.displayHeader("Queue Donation");

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
                PriorityFIFO.queueFIFO(aidsCompletedList, input);
                break;
            case 2:
                PriorityQ.queuePriority(aidsCompletedList, input);
                break;
            case 3:
                break;
        }
    }

    static class PriorityQ {

        public PriorityQ() {
        }

        public static void queuePriority(ArrayList<AidsCompleted> aidsCompletedList, Scanner input) {

            PriorityQueue<AidPriority> aidList = new PriorityQueue<AidPriority>(11, new AidComparator());

            int choice;
            String Ngo = "";

            AidSystem.displayHeader("Queue Donation");

            do {
                System.out.println("Select an option:");
                System.out.println("1. Enqueue an NGO");
                System.out.println("2. Dequeue an NGO");
                System.out.println("3. exit");

                System.out.print("[ ");

                for (AidPriority s : aidList) {
                    System.out.print(s.ngo + " ");
                }

                System.out.println(" ] ");

                System.out.print("Command >");
                // choice = getMenuInput();

                choice = input.nextInt();

                priorityActions(choice, Ngo, aidsCompletedList, input, aidList);

            } while (choice != 3);

        }

        public static void priorityActions(int choice, String Ngo, ArrayList<AidsCompleted> aidsCompletedList,
                Scanner input, PriorityQueue<AidPriority> aidList) {

            switch (choice) {
                case 1:
                    System.out.println("Which Ngo?");

                    Ngo = input.next();
                    // if there is ngo name like that
                    // if ngo is already in the queue
                    Enqueue(Ngo, aidsCompletedList, input, aidList);
                    break;
                case 2:

                    Dequeue(Ngo, aidsCompletedList, input, aidList);
                    break;
                case 3:
                    break;
            }
        }

        public static void Enqueue(String Ngo, ArrayList<AidsCompleted> aidsCompletedList, Scanner input,
                PriorityQueue<AidPriority> aidList) {

            boolean noNGO = false;
            String errorMessage = "";
            int manpower;

            if (aidList.isEmpty()) {
                System.out.println("masuk kat empty");
                checkAvailable(Ngo, aidsCompletedList, errorMessage, noNGO);
            } else {
                checkAvailableAid(Ngo, aidList, errorMessage, noNGO);
                checkAvailable(Ngo, aidsCompletedList, errorMessage, noNGO);
            }

            if (!noNGO) {
                manpower = checkHighestManpower(Ngo, aidsCompletedList);

                System.out.print(manpower);
                AidPriority aid = new AidPriority(Ngo, manpower);
                aidList.add(aid);

            } else {
                System.out.println(errorMessage + ". Please try again");
            }

        }

        public static int checkHighestManpower(String Ngo, ArrayList<AidsCompleted> aidsCompletedList) {

            int manpower = 0;

            for (AidsCompleted s : aidsCompletedList) {
                if (s.getngoName().equals(Ngo) && s.getStatus().equals("Reserved")
                        && Integer.parseInt(s.getManpower()) > manpower) {
                    manpower = Integer.parseInt(s.getManpower());
                }
            }

            return manpower;
        }

        public static void Dequeue(String Ngo, ArrayList<AidsCompleted> aidsCompletedList, Scanner input,
                PriorityQueue<AidPriority> aidList) {

            boolean noNGO = false;
            String errorMessage = "";

            if (aidList.isEmpty()) {
                noNGO = false;
            } else {
                checkNotAvailableAid(Ngo, aidList, errorMessage, noNGO);
            }

            if (!noNGO) {

                Ngo = aidList.peek().getNgo();
                aidList.remove();
                setCompleted(Ngo, aidsCompletedList);

                try {
                    AidsCompleted.writeAidsCompletedFile(aidsCompletedList);
                } catch (IOException e) {
                    System.out.println("file cannot write");
                }

            } else {
                System.out.println(errorMessage + ". Please try again");
            }

        }

        public static void setCompleted(String Ngo, ArrayList<AidsCompleted> aidsCompletedList) {
            for (AidsCompleted s : aidsCompletedList) {
                if (s.getngoName().equals(Ngo) && s.getStatus().equals("Reserved")) {
                    s.setStatus("Completed");
                }
            }
        }

        public static void checkNotAvailableAid(String Ngo, PriorityQueue<AidPriority> aidList, String errorMessage,
                boolean noNGO) {

            for (AidPriority s : aidList) {
                if (s.getNgo().equals(Ngo)) {
                    noNGO = true;
                    break;
                } else {
                    noNGO = false;
                }
            }
            errorMessage = "sorry, that ngo is not in queue";
        }

        public static void checkAvailableAid(String Ngo, PriorityQueue<AidPriority> aidList, String errormessage,
                boolean noNGO) {

            for (AidPriority s : aidList) {
                if (s.getNgo().equals(Ngo)) {
                    noNGO = false;
                    break;
                } else {
                    noNGO = true;
                }
            }
            errormessage = "sorry, ngo is already in queue";
        }

        public static void checkAvailable(String Ngo, ArrayList<AidsCompleted> aidsCompletedList, String errormessage,
                boolean noNGO) {

            for (AidsCompleted s : aidsCompletedList) {
                if (s.getngoName().equals(Ngo)) {
                    System.out.println("masuk kat ngo");
                    noNGO = true;
                    break;
                }
            }
            errormessage = "sorry, no NGO found";
        }
    }

    static class PriorityFIFO {

        public PriorityFIFO() {
        }

        public static void queueFIFO(ArrayList<AidsCompleted> aidsCompletedList, Scanner input) {

            Queue<AidPriority> aidList = new LinkedList<AidPriority>();
            // listReserved(reservedList, aidsCompletedList);

            int choice;
            String Ngo = "";

            AidSystem.displayHeader("Queue Donation");

            do {
                System.out.println("Select an option:");
                System.out.println("1. Enqueue an NGO");
                System.out.println("2. Dequeue an NGO");
                System.out.println("3. exit");

                System.out.print("[ ");

                for (AidPriority s : aidList) {
                    System.out.print(s.ngo + " ");
                }

                System.out.println(" ] ");

                System.out.print("Command >");
                // choice = getMenuInput();

                choice = input.nextInt();

                priorityActions(choice, Ngo, aidsCompletedList, input, aidList);

            } while (choice != 3);
        }

        public static void priorityActions(int choice, String Ngo, ArrayList<AidsCompleted> aidsCompletedList,
                Scanner input, Queue<AidPriority> aidList) {

            switch (choice) {
                case 1:
                    System.out.println("Which Ngo?");

                    Ngo = input.next();
                    // if there is ngo name like that
                    // if ngo is already in the queue
                    Enqueue(Ngo, aidsCompletedList, input, aidList);
                    break;
                case 2:

                    Dequeue(Ngo, aidsCompletedList, input, aidList);
                    break;
                case 3:
                    break;
            }
        }

        public static void Enqueue(String Ngo, ArrayList<AidsCompleted> aidsCompletedList, Scanner input,
                Queue<AidPriority> aidList) {

            boolean noNGO = false;
            String errorMessage = "";
            int manpower = 0;

            if (aidList.isEmpty()) {
                System.out.println("masuk kat empty");
                checkAvailable(Ngo, aidsCompletedList, errorMessage, noNGO);
            } else {
                checkAvailableAid(Ngo, aidList, errorMessage, noNGO);
                checkAvailable(Ngo, aidsCompletedList, errorMessage, noNGO);
            }

            if (!noNGO) {

                AidPriority aid = new AidPriority(Ngo, manpower);
                aidList.add(aid);
            } else {
                System.out.println(errorMessage + ". Please try again");
            }

        }

        public static void setCompleted(String Ngo, ArrayList<AidsCompleted> aidsCompletedList) {
            for (AidsCompleted s : aidsCompletedList) {
                if (s.getngoName().equals(Ngo) && s.getStatus().equals("Reserved")) {
                    s.setStatus("Completed");
                }
            }
        }

        public static void checkNotAvailableAid(String Ngo, Queue<AidPriority> aidList, String errorMessage,
                boolean noNGO) {

            for (AidPriority s : aidList) {
                if (s.getNgo().equals(Ngo)) {
                    noNGO = true;
                    break;
                } else {
                    noNGO = false;
                }
            }
            errorMessage = "sorry, that ngo is not in queue";
        }

        public static void checkAvailableAid(String Ngo, Queue<AidPriority> aidList, String errormessage,
                boolean noNGO) {

            for (AidPriority s : aidList) {
                if (s.getNgo().equals(Ngo)) {
                    noNGO = false;
                    break;
                } else {
                    noNGO = true;
                }
            }
            errormessage = "sorry, ngo is already in queue";
        }

        public static void checkAvailable(String Ngo, ArrayList<AidsCompleted> aidsCompletedList, String errormessage,
                boolean noNGO) {

            for (AidsCompleted s : aidsCompletedList) {
                if (s.getngoName().equals(Ngo)) {
                    System.out.println("masuk kat ngo");
                    noNGO = true;
                    break;
                }
            }
            errormessage = "sorry, no NGO found";
        }

        public static void Dequeue(String Ngo, ArrayList<AidsCompleted> aidsCompletedList, Scanner input,
                Queue<AidPriority> aidList) {

            boolean noNGO = false;
            String errorMessage = "";

            if (aidList.isEmpty()) {
                noNGO = false;
            } else {
                checkNotAvailableAid(Ngo, aidList, errorMessage, noNGO);
            }

            if (!noNGO) {

                Ngo = aidList.peek().getNgo();
                aidList.remove();
                setCompleted(Ngo, aidsCompletedList);

                try {
                    AidsCompleted.writeAidsCompletedFile(aidsCompletedList);
                } catch (IOException e) {
                    System.out.println("file cannot write");
                }

            } else {
                System.out.println(errorMessage + ". Please try again");
            }

        }
    }
}