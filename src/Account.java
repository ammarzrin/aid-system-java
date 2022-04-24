package src;

import java.util.ArrayList;

/**
 * A superclass named Account which stores the username and password.
 */
public abstract class Account {
    private String username;
    private String password;

    /**
     * No-arg constructor for Account class
     */
    public Account() {
    }

    /**
     * Constructs an account with specified username and passowrd
     * 
     * @param username
     * @param password
     */
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Sets the username for the Account object.
     * 
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets the password for the Account object.
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the Account object's username.
     * 
     * @return Returns account's username.
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Get the Account object's password.
     * 
     * @return Returns account's password.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * A function to view all donations made so far to the DC including their
     * status.
     */
    public static void viewAllDonations(ArrayList<AidsCompleted> aidsCompletedList) {
        AidSystem.displayHeader("All Donations");
        System.out.printf("%10s\t%12s\t%10s\t%8s\t%7s\t%8s\t%10s",
                "Name", "Phone", "Item", "Quantity", "To NGO", "Manpower", "Status\n");
        for (AidsCompleted s : aidsCompletedList) {
            System.out.println(s);
        }
    }
}
