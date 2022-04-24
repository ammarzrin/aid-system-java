package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is create an object of an officially donated item to be stored
 * together as an object which combines data fields from different classes.
 */
public class AidsCompleted {
    public String donor;
    public String phoneNumber;
    public String aid;
    public String ngoName;
    public int quantity;
    public String manpower;
    public String status;

    /**
     * Constructs an aid object to later be stored in aidsCompleted.csv file.
     * 
     * @param donor       Donor's username
     * @param phoneNumber Donor's phone number
     * @param aid         Aid item title
     * @param ngoName     NGO's initials
     * @param quantity    Aid item quantity
     * @param manpower    NGO's manpower number
     * @param status      Aid status (Available, Reserved, or Collected)
     */
    public AidsCompleted(String donor, String phoneNumber, String aid, String ngoName, int quantity, String manpower,
            String status) {
        this.donor = donor;
        this.phoneNumber = phoneNumber;
        this.aid = aid;
        this.ngoName = ngoName;
        this.quantity = quantity;
        this.manpower = manpower;
        this.status = status;
    }

    /**
     * Gets status of the aid object.
     * 
     * @return Returns either Available, Reserved, or Collected.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets item donor's username.
     * 
     * @return Returns the item donor's username.
     */
    public String getDonor() {
        return donor;
    }

    /**
     * Gets the phone number of the donor of item.
     * 
     * @return Returns the phone number of the item donor.
     */
    public String getPhone() {
        return phoneNumber;
    }

    /**
     * Gets the donated item's title.
     * 
     * @return Returns aid value.
     */
    public String getAid() {
        return aid;
    }

    /**
     * Gets quantity of donated aid item.
     * 
     * @return Returns the quantity of the donated aid item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the NGO's name assigned to the donated item.
     * 
     * @return Returns the NGO's name assigned to the donated item.
     */
    public String getngoName() {
        return ngoName;
    }

    /**
     * Gets the NGO's manpower.
     * 
     * @return Returns the NGO's manpower value.
     */
    public String getManpower() {
        return manpower;
    }

    /**
     * Sets the manpower number of the NGO to the AidsCompleted object.
     * 
     * @param manpower Manpower number of an NGO
     */
    public void setManpower(String manpower) {
        this.manpower = manpower;
    }

    /**
     * Sets the NGO name to the assigned NGO's initials.
     * 
     * @param ngoName NGO name assigned to the donated item
     */
    public void setngoName(String ngoName) {
        this.ngoName = ngoName;
    }

    /**
     * Sets quantity of donated item.
     * 
     * @param quantity Donated aid item quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets the status of the donated item.
     * 
     * @param status Status of "Available", "Reserved", or "Collected"
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Converts object values to a string that follows a tabular format.
     */
    public String toString() {
        return String.format("%10s\t%12s\t%10s\t%8d\t%7s\t%8s\t%10s",
                getDonor(), getPhone(), getAid(), getQuantity(), getngoName(), getManpower(), getStatus());
    }

    /**
     * Creates a string of donated item's details to be stored in the data file.
     * 
     * @return Returns a suitable string for writing into the CSV file.
     */
    public String toCSVString() {
        return getDonor() + "," + getPhone() + "," + getAid() + "," + getngoName() + "," + getQuantity() + ","
                + getManpower() + "," + getStatus();
    }

    /**
     * Reads the aidsCompleted.csv file to store into list of all donations made.
     * 
     * @return Returns list of donations made to the system.
     * @throws IOException
     */
    public static ArrayList<AidsCompleted> readAidsCompletedFile() throws IOException {
        ArrayList<AidsCompleted> aidsCompletedList = new ArrayList<AidsCompleted>();
        List<String> completed = Files.readAllLines(Paths.get("src/aidsCompleted.csv"));
        // System.out.println("Array:");
        // for (String s : completed)
        // System.out.println(s);
        for (int i = 0; i < completed.size(); i++) {
            String[] items = completed.get(i).split(",");
            aidsCompletedList.add(new AidsCompleted(items[0], items[1], items[2], items[3], Integer.parseInt(items[4]),
                    items[5], items[6]));
        }
        return aidsCompletedList;
    }

    /**
     * Writes the donation details onto the aidsCompleted.csv.
     * 
     * @param aid AidsCompleted object having details of the donated item.
     * @throws IOException
     */
    public static void writeAidsCompletedFile(ArrayList<AidsCompleted> aid)
            throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < aid.size(); i++)
            sb.append(aid.get(i).toCSVString() + "\n");
        Files.write(Paths.get("src/aidsCompleted.csv"), sb.toString().getBytes());
    }
}
