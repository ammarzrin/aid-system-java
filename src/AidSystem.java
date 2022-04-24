package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main file. Run this to start the program.
 */
public class AidSystem {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        Donor userD = new Donor();
        NGO userN = new NGO();
        int userType = -1;

        userType = UserAuth.welcomePage(userD, userN, userType);
        ArrayList<Request> requestList = Request.readRequestFile();
        ArrayList<AidsCompleted> aidsCompletedList = AidsCompleted.readAidsCompletedFile();

        if (userType == 0) {
            Donor.menu(userD, requestList, aidsCompletedList, input);
        } else if (userType == 1) { // userType == 1
            NGO.menu(userN, requestList, aidsCompletedList, input);
        }
    }

    /**
     * Creates a frame for the current page's header. UI/UX purposes.
     * 
     * @param message The header title.
     */
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
