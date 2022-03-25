package donations;

import java.util.LinkedList;
import java.util.Queue;

public class Aid extends Donations {
    public String name;
    public String phone;

    public Aid(String name, String phone, String aid, int quantity) {
        this.name = name;
        this.phone = phone;
        this.aid = aid;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDonorName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAid() {
        return aid;
    }

    public int getQuantity() {
        return quantity;
    }

    public String toString() {
        return "Donor: name = " + getDonorName() + ", Phone = " + getPhone() + ", aid = " + getAid() + ", quantity = "
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
                aidCompleted.add(new AidsCompleted(aid.getDonorName(), aid.getPhone(), aid.getAid(),
                        requestListTemp.peek().getngoName(), requestListTemp.peek().getQuantity(),
                        requestListTemp.peek().getManpower()));
                aid.setQuantity(aid.getQuantity() - requestListTemp.peek().getQuantity());
                requestListTemp.remove();
                // code to remove the request from the list cause its finished
            } else if (index == -1) {
                aidCompleted.add(new AidsCompleted(aid.getDonorName(), aid.getPhone(), aid.getAid(),
                        requestListTemp.peek().getngoName(), aid.getQuantity(), requestListTemp.peek().getManpower()));
                requestListTemp.peek().setQuantity(requestListTemp.peek().getQuantity() - aid.getQuantity());
                break;
                // no remove request cause not done yet
            } else {
                System.out.println("hoho");
                aidCompleted.add(new AidsCompleted(aid.getDonorName(), aid.getPhone(), aid.getAid(),
                        requestListTemp.peek().getngoName(), aid.getQuantity(), requestListTemp.peek().getManpower()));
                // remove request from the list
                requestListTemp.remove();
                break;
            }
        }
    }
}
