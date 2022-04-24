package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AidsCompleted {
    public String donor;
    public String phoneNumber;
    public String aid;
    public String ngoName;
    public int quantity;
    public String manpower;
    public String status;

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

    public String getStatus() {
        return status;
    }

    public String getDonor() {
        return donor;
    }

    public String getPhone() {
        return phoneNumber;
    }

    public String getAid() {
        return aid;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getngoName() {
        return ngoName;
    }

    public String getManpower() {
        return manpower;
    }

    public void setManpower(String manpower) {
        this.manpower = manpower;
    }

    public void setngoName(String ngoName) {
        this.ngoName = ngoName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        // donor, phone, item, quantity, to ngo, manpower, status
        return String.format("%10s\t%12s\t%10s\t%8d\t%7s\t%8s\t%10s",
                getDonor(), getPhone(), getAid(), getQuantity(), getngoName(), getManpower(), getStatus());
    }

    public String toCSVString() {
        return getDonor() + "," + getPhone() + "," + getAid() + "," + getngoName() + "," + getQuantity() + ","
                + getManpower() + "," + getStatus();
    }

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

    public static void writeAidsCompletedFile(ArrayList<AidsCompleted> aid)
            throws IOException {
        // read students.csv into a list of lines.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < aid.size(); i++)
            sb.append(aid.get(i).toCSVString() + "\n");
        Files.write(Paths.get("src/aidsCompleted.csv"), sb.toString().getBytes());
    }
}
