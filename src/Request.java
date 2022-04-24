package src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Request extends NGO {

    public String aid;
    public int quantity;

    public Request(String ngoName, int manpower, String aid, int quantity) {
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

    public String toString() {
        return String.format("%5s\t%8s\t%10s\t%8d",
                getNGO(), getManpower(), getAid(), getQuantity());
    }

    public String toCSVString() {
        return getNGO() + "," + getManpower() + "," + getAid() + "," + getQuantity();
    }

    public int Compare(int a, int b) {
        if (a > b) // if quantity donor is bigger than quantity ngoName
            return 1;
        else if (a < b) // if quantity donor is smaller than quantity ngoName
            return -1;
        else
            return 0; // if quantity == quantity

    }

    public static void matchRequest(Request req, ArrayList<Request> requestList,
            ArrayList<AidsCompleted> aidCompleted) {
        for (int i = 0; aidCompleted.size() > i; i++) {
            int index = req.Compare(aidCompleted.get(i).getQuantity(), req.getQuantity());
            String requesting = aidCompleted.get(i).getAid();
            String aiding = req.getAid();
            if (requesting.equals(aiding) && aidCompleted.get(i).getStatus().equals("Available")) {
                if (index == 1) {
                    aidCompleted.add(new AidsCompleted(aidCompleted.get(i).getDonor(), aidCompleted.get(i).getPhone(),
                            req.getAid(), req.setNull(), aidCompleted.get(i).getQuantity() - req.getQuantity(),
                            req.setNull(), req.setAvailable()));
                    aidCompleted.get(i).setManpower(Integer.toString(req.getManpower()));
                    aidCompleted.get(i).setngoName(req.getNGO());
                    aidCompleted.get(i).setQuantity(req.getQuantity());
                    aidCompleted.get(i).setStatus(req.setReserved());
                    req.setQuantity(0);
                    break;
                    // code to remove the aid from the list cause its finished
                } else if (index == -1) {
                    aidCompleted.get(i).setManpower(Integer.toString(req.getManpower()));
                    aidCompleted.get(i).setngoName(req.getNGO());
                    aidCompleted.get(i).setStatus(req.setReserved());
                    req.setQuantity(req.getQuantity() - aidCompleted.get(i).getQuantity());
                    // no remove request cause not done yet
                } else {
                    aidCompleted.get(i).setManpower(Integer.toString(req.getManpower()));
                    aidCompleted.get(i).setngoName(req.getNGO());
                    aidCompleted.get(i).setStatus(req.setReserved());
                    // remove request from the list
                    req.setQuantity(0);
                    break;
                }
            }
        }
    }

    public static ArrayList<Request> readRequestFile() throws IOException {
        ArrayList<Request> requestList = new ArrayList<Request>();
        List<String> requests = Files.readAllLines(Paths.get("src/requests.csv"));
        for (int i = 0; i < requests.size(); i++) {
            String[] items = requests.get(i).split(",");
            requestList.add(new Request(items[0], Integer.parseInt(items[1]), items[2], Integer.parseInt(items[3])));
        }
        return requestList;
    }

    public static void writeRequestFile(ArrayList<Request> request)
            throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < request.size(); i++)
            sb.append(request.get(i).toCSVString() + "\n");
        Files.write(Paths.get("src/requests.csv"), sb.toString().getBytes());
    }
}
