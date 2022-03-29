package roles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

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

    public String getAid() {
        return aid;
    }

    public int getQuantity() {
        return quantity;
    }

    public String toString() {
        return "Donor: name = " + getName() + ", Phone = " + getPhone() + ", aid = " + getAid() + ", quantity = "
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

    public static void matchDonation(Aid aid, Queue<Request> requestListTemp, LinkedList<AidsCompleted> aidCompleted) {

        while (requestListTemp.size() > 0) {
            int index = aid.Compare(aid.getQuantity(), requestListTemp.peek().getQuantity());

            System.out.println(index);
            System.out.println(requestListTemp.peek().getManpower());

            if (index == 1) {
                System.out.println("hihi");
                aidCompleted.add(new AidsCompleted(aid.getName(), aid.getPhone(), aid.getAid(),
                        requestListTemp.peek().getNGO(), requestListTemp.peek().getQuantity(),
                        requestListTemp.peek().getManpower()));
                aid.setQuantity(aid.getQuantity() - requestListTemp.peek().getQuantity());
                requestListTemp.remove();
                // code to remove the request from the list cause its finished
            } else if (index == -1) {
                aidCompleted.add(new AidsCompleted(aid.getName(), aid.getPhone(), aid.getAid(),
                        requestListTemp.peek().getNGO(), aid.getQuantity(), requestListTemp.peek().getManpower()));
                requestListTemp.peek().setQuantity(requestListTemp.peek().getQuantity() - aid.getQuantity());
                break;
                // no remove request cause not done yet
            } else {
                System.out.println("hoho");
                aidCompleted.add(new AidsCompleted(aid.getName(), aid.getPhone(), aid.getAid(),
                        requestListTemp.peek().getNGO(), aid.getQuantity(), requestListTemp.peek().getManpower()));
                // remove request from the list
                requestListTemp.remove();
                break;
            }
        }
    }

    public static Queue<Aid> readAidFile() throws IOException {
        Queue<Aid> aidList = new LinkedList<Aid>();
        List<String> aids = Files.readAllLines(Paths.get("userdata/aids.csv"));
        System.out.println("Array:");
        for (String s : aids)
            System.out.println(s);
        for (int i = 1; i < aids.size(); i++) {
            String[] items = aids.get(i).split(",");
            aidList.add(new Aid(items[0], items[1], items[2], Integer.parseInt(items[3])));
        }
        return aidList;
    }

    public static void writeAidFile(Queue<Aid> aids) throws IOException {
        OutputStream output = new BufferedOutputStream(new FileOutputStream("userdata/aids.csv", false));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));

        writer.write("name" + "," + "phone" + "," + "aid" + "," + "quantity");
        writer.newLine();

        for (Aid a : aids) {
            writer.write(a.getName() + "," + a.getPhone() + "," + a.getAid() + "," + a.getQuantity());
            writer.newLine();
        }

        writer.close();
        output.close();
    }

}
