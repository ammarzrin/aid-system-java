package main;

import java.io.IOException;
import java.util.Queue;

import javax.xml.namespace.QName;

import roles.Aid;
import roles.Donor;
import roles.NGO;
import roles.Request;

// Main program. Run this.
public class AidSystem {
    public static void main(String[] args) throws IOException {
        Donor userD = new Donor();
        NGO userN = new NGO();
        int userType = -1;
        int choice = 0;

        userType = UserAuth.welcomePage(userD, userN, userType);
        if (userType == 0) {

            Queue<Aid> aidList = Aid.readAidFile();
            Queue<Request> requestList = Request.readRequestFile();

            for (Aid s : aidList) {
                System.out.println(s);
            }
            for (Request s : requestList) {
                System.out.println(s);
            }

            Donor.menu(choice, userD);
            System.out.println("hi im your mom");
            System.out.println(choice);

        } else if (userType == 1) { // userType == 1
            do {
                choice = NGO.menu(userN);
            } while (choice != 5);
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
