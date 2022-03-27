import java.io.IOException;

import roles.Donor;
import roles.NGO;

// Main program. Run this.
public class AidSystem {
    public static void main(String[] args) throws IOException {
        new UserAuth();
        boolean programRun = true;
        int userType;
        String username = "ammarzrin"; // need to take currentUser's username
        do {
            userType = UserAuth.checkUserType(username);
            if (userType == 0) {
                Donor.menu();
                programRun = false;
            } else { // userType == 1
                NGO.menu();
                programRun = false;
            }
        } while (programRun);
        // after user login, do Donor currentUser = new Donor(currentUser details)
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
