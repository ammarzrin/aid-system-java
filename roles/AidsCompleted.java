package roles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AidsCompleted {
    public String donor;
    public String phoneNumber;
    public String aid;
    public String ngoName;
    public int quantity;
    public int manpower;

    public AidsCompleted(String donor, String phoneNumber, String aid, String ngoName, int quantity, int manpower) {
        this.donor = donor;
        this.phoneNumber = phoneNumber;
        this.aid = aid;
        this.ngoName = ngoName;
        this.quantity = quantity;
        this.manpower = manpower;
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

    public int getManpower() {
        return manpower;
    }

    public String toString() {
        return "Donor: donor = " + getDonor() + ", Phone = " + getPhone() + ", aid = " + getAid() + ", ngo = "
                + getngoName() + ", quantity = "
                + getQuantity() + ", manpower = " + getManpower();
    }

    public static Queue<AidsCompleted> readAidsCompletedFile() throws IOException {
        Queue<AidsCompleted> aidsCompletedList = new LinkedList<AidsCompleted>();
        List<String> completed = Files.readAllLines(Paths.get("userdata/requests.csv"));
        System.out.println("Array:");
        for (String s : completed)
            System.out.println(s);
        for (int i = 1; i < completed.size(); i++) {
            String[] items = completed.get(i).split(",");
            aidsCompletedList.add(new AidsCompleted(items[0], items[1], items[2], items[3], Integer.parseInt(items[4]),
                    Integer.parseInt(items[5])));
        }
        return aidsCompletedList;
    }

}
