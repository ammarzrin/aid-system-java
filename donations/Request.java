package donations;

import java.util.LinkedList;
import java.util.Queue;

public class Request extends Donations {
    public String ngoName;
    public int manpower;

    public Request(String ngoName, String aid, int manpower, int quantity) {
        this.ngoName = ngoName;
        this.manpower = manpower;
        this.aid = aid;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getngoName() {
        return ngoName;
    }

    public int getManpower() {
        return manpower;
    }

    public String getAid() {
        return aid;
    }

    public int getQuantity() {
        return quantity;
    }

    public String toString() {
        return "ngoName :  name = " + getngoName() + ", Manpower = " + getManpower() + ", aid = " + getAid()
                + ", quantity = "
                + getQuantity();
    }

    public static void getrequestList(String aid, LinkedList<Request> requestList, Queue<Request> requestListTemp) {
        for (Request r : requestList) {
            if (aid.equals(r.getAid())) {
                requestListTemp.offer(new Request(r.getngoName(), r.getAid(), r.getManpower(), r.getQuantity()));
            }
        }
    }

    public int Compare(int a, int b) {
        if (a > b) // if quantity donor is bigger than quantity ngoName
            return 1;
        else if (a < b) // if quantity donor is smaller than quantity ngoName
            return -1;
        else
            return 0; // if quantity == quantity

    }

    public static void matchRequest(Request req, Queue<Aid> aidListTemp, LinkedList<AidsCompleted> aidCompleted) {

        while (aidListTemp.size() > 0) {

            int index = req.Compare(aidListTemp.peek().getQuantity(), req.getQuantity());

            System.out.println(index);

            if (index == 1) {
                System.out.println("hihi");
                req.setQuantity(req.getQuantity() - aidListTemp.peek().getQuantity());
                aidCompleted.add(new AidsCompleted(aidListTemp.peek().getDonorName(), aidListTemp.peek().getPhone(),
                        req.getAid(), req.getngoName(), req.getQuantity(), req.getManpower()));
                aidListTemp.remove();
                // code to remove the request from the list cause its finished
            } else if (index == -1) {
                aidCompleted.add(new AidsCompleted(aidListTemp.peek().getDonorName(), aidListTemp.peek().getPhone(),
                        req.getAid(), req.getngoName(), req.getQuantity(), req.getManpower()));
                aidListTemp.peek().setQuantity(aidListTemp.peek().getQuantity() - req.getQuantity());
                break;
                // no remove request cause not done yet
            } else {
                System.out.println("hoho");
                aidCompleted.add(new AidsCompleted(aidListTemp.peek().getDonorName(), aidListTemp.peek().getPhone(),
                        req.getAid(), req.getngoName(), req.getQuantity(), req.getManpower()));
                // remove request from the list
                aidListTemp.remove();
                break;
            }
        }
    }

}
