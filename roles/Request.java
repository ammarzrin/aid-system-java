package roles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Request extends NGO {

    public String aid;
    public int quantity;

    public Request(String ngoName, String aid, int manpower, int quantity) {
        this.ngoname = ngoName;
        this.manpower = manpower;
        this.aid = aid;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getAid() {
        return aid;
    }

    public int getQuantity() {
        return quantity;
    }

    public String toString() {
        return "ngoName :  name = " + getNGO() + ", Manpower = " + getManpower() + ", aid = " + getAid()
                + ", quantity = "
                + getQuantity();
    }

    public static void getrequestList(String aid, LinkedList<Request> requestList, Queue<Request> requestListTemp) {
        for (Request r : requestList) {
            if (aid.equals(r.getAid())) {
                requestListTemp.offer(new Request(r.getNGO(), r.getAid(), r.getManpower(), r.getQuantity()));
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
                aidCompleted.add(new AidsCompleted(aidListTemp.peek().getName(), aidListTemp.peek().getPhone(),
                        req.getAid(), req.getNGO(), req.getQuantity(), req.getManpower()));
                aidListTemp.remove();
                // code to remove the request from the list cause its finished
            } else if (index == -1) {
                aidCompleted.add(new AidsCompleted(aidListTemp.peek().getName(), aidListTemp.peek().getPhone(),
                        req.getAid(), req.getNGO(), req.getQuantity(), req.getManpower()));
                aidListTemp.peek().setQuantity(aidListTemp.peek().getQuantity() - req.getQuantity());
                break;
                // no remove request cause not done yet
            } else {
                System.out.println("hoho");
                aidCompleted.add(new AidsCompleted(aidListTemp.peek().getName(), aidListTemp.peek().getPhone(),
                        req.getAid(), req.getNGO(), req.getQuantity(), req.getManpower()));
                // remove request from the list
                aidListTemp.remove();
                break;
            }
        }
    }

    public static Queue<Request> readRequestFile() throws IOException {
        Queue<Request> requestList = new LinkedList<Request>();
        List<String> requests = Files.readAllLines(Paths.get("userdata/requests.csv"));
        System.out.println("Array:");
        for (String s : requests)
            System.out.println(s);
        for (int i = 1; i < requests.size(); i++) {
            String[] items = requests.get(i).split(",");
            requestList.add(new Request(items[0], items[1], Integer.parseInt(items[2]), Integer.parseInt(items[3])));
        }
        return requestList;
    }
}
