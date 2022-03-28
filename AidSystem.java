import java.io.IOException;

import roles.Donor;
import roles.NGO;

// Main program. Run this.
public class AidSystem {
    public static void main(String[] args) throws IOException {
        Donor userD = new Donor();
        NGO userN = new NGO();
        int userType = 0;
        userType = UserAuth.welcomePage(userD, userN, userType);
        if (userType == 0) {
            Donor.menu(userD); // HELP!
        } else { // userType == 1
            NGO.menu(userN);
        }
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
