package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Aid extends Donor {

    public int quantity;
    public String aid;

    public Aid() {
    }

    public Aid(String name, String phone, String aid, int quantity) {
        this.name = name;
        this.phone = phone;
        this.aid = aid;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String setReserved() {
        return "Reserved";
    }

    public String setCollected() {
        return "Collected";
    }

    public String setAvailable() {
        return "Available";
    }

    public String setNull() {
        return "-";
    }

    public String getAid() {
        return aid;
    }

    public int getQuantity() {
        return quantity;
    }

    public String toString() {
        return "Donor: Name = " + getName() + ", Phone = " + getPhone() + ", Aid = " + getAid() + ", Quantity = "
                + getQuantity();
    }

    public int Compare(int a, int b) {
        if (a > b) // if quantity donor is bigger than quantity ngoName
            return 1;
        else if (a < b) // if quantity donor is smaller than quantity ngoName
            return -1;
        else
            return 0; // if quantity == quantity
    }

    public String toCSVString() {
        return getName() + "," + getPhone() + "," + getAid() + "," + getQuantity();
    }

    public static void matchDonation(Aid aid, ArrayList<Request> requestListTemp,
            ArrayList<AidsCompleted> aidCompleted) {

        for (int i = 0; requestListTemp.size() > i; i++) {

            int index = aid.Compare(aid.getQuantity(), requestListTemp.get(i).getQuantity());

            String requesting = requestListTemp.get(i).getAid();
            String aiding = aid.getAid();

            if (requesting.equals(aiding)) {

                if (index == 1) {

                    aidCompleted.add(new AidsCompleted(aid.getName(), aid.getPhone(), aid.getAid(),
                            requestListTemp.get(i).getNGO(), requestListTemp.get(i).getQuantity(),
                            Integer.toString(requestListTemp.get(i).getManpower()), aid.setReserved()));

                    aid.setQuantity(aid.getQuantity() - requestListTemp.get(i).getQuantity());
                    requestListTemp.remove(i);
                    i--;

                    // code to remove the request from the list cause its finished
                } else if (index == -1) {

                    aidCompleted.add(new AidsCompleted(aid.getName(), aid.getPhone(), aid.getAid(),
                            requestListTemp.get(i).getNGO(), aid.getQuantity(),
                            Integer.toString(requestListTemp.get(i).getManpower()), aid.setReserved()));
                    requestListTemp.get(i).setQuantity(requestListTemp.get(i).getQuantity() - aid.getQuantity());
                    aid.setQuantity(0);
                    break;
                    // no remove request cause not done yet
                } else {
                    System.out.println("hoho");
                    aidCompleted.add(new AidsCompleted(aid.getName(), aid.getPhone(), aid.getAid(),
                            requestListTemp.get(i).getNGO(), aid.getQuantity(),
                            Integer.toString(requestListTemp.get(i).getManpower()), aid.setReserved()));
                    // remove request from the list
                    requestListTemp.remove(i);
                    aid.setQuantity(0);
                    break;
                }
            }
        }
    }

    public static ArrayList<Aid> readAidFile() throws IOException {
        ArrayList<Aid> aidList = new ArrayList<Aid>();
        List<String> aids = Files.readAllLines(Paths.get("src/aids.csv"));
        System.out.println("Array:");
        for (String s : aids)
            System.out.println(s);
        for (int i = 0; i < aids.size(); i++) {
            String[] items = aids.get(i).split(",");
            aidList.add(new Aid(items[0], items[1], items[2], Integer.parseInt(items[3])));
        }
        return aidList;
    }

    public static void writeAidFile(ArrayList<Aid> aid)
            throws IOException {
        // read students.csv into a list of lines.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < aid.size(); i++)
            sb.append(aid.get(i).toCSVString() + "\n");
        Files.write(Paths.get("src/aids.csv"), sb.toString().getBytes());
    }

}
