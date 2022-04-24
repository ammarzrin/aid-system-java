package src;

import java.util.ArrayList;

/**
 * Aid class is a subclass under Donor which stores details for donations.
 */
public class Aid extends Donor {

    public int quantity;
    public String aid;

    /**
     * No-arg constructor
     */
    public Aid() {
    }

    /**
     * Constructs a new Aid object that has Donor's details along with the aid and
     * quantity.
     * 
     * @param name     Donor's name
     * @param phone    Donor's phone number
     * @param aid      Donated item
     * @param quantity Quantity of donated item
     */
    public Aid(String name, String phone, String aid, int quantity) {
        this.name = name;
        this.phone = phone;
        this.aid = aid;
        this.quantity = quantity;
    }

    /**
     * Sets the quantity of an aid object.
     * 
     * @param quantity Quantity of donated item
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Sets the donated item of an aid object.
     * 
     * @param aid Donated item
     */
    public void setAid(String aid) {
        this.aid = aid;
    }

    /**
     * Sets the aid status to reserved when assigned to an NGO.
     * 
     * @return Returns a "Reserved" string to update Aid status.
     */
    public String setReserved() {
        return "Reserved";
    }

    /**
     * Sets the aid status to collected when an NGO have collected the item.
     * 
     * @return Returns a "Collected" string to update Aid status.
     */
    public String setCollected() {
        return "Collected";
    }

    /**
     * Sets the aid status to available when no NGOs have yet been assigned to the
     * item.
     * 
     * @return Returns a "Available" string to update Aid status.
     */
    public String setAvailable() {
        return "Available";
    }

    /**
     * Sets NGO and Manpower to null when an aid item is not reserved nor collected.
     * 
     * @return Returns a "-" string to indicate the aid's available status.
     */
    public String setNull() {
        return "-";
    }

    /**
     * Gets aid item title.
     * 
     * @return Returns aid item.
     */
    public String getAid() {
        return aid;
    }

    /**
     * Gets quantity of aid item.
     * 
     * @return Returns quantity of the aid item.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Default print value when printing Aid items.
     */
    public String toString() {
        return "Donor: Name = " + getName() + ", Phone = " + getPhone() + ", Aid = " + getAid() + ", Quantity = "
                + getQuantity();
    }

    /**
     * Compares between 2 different quantities
     * 
     * @param a Donor's aid item quantity
     * @param b NGO's request item quantity
     * @return Returns 1 if Donor's item quantity is bigger than NGO's request
     *         quantity
     */
    public int Compare(int a, int b) {
        if (a > b) // if quantity donor is bigger than quantity ngoName
            return 1;
        else if (a < b) // if quantity donor is smaller than quantity ngoName
            return -1;
        else
            return 0; // if quantity == quantity
    }

    /**
     * Printing an object is formatted to be put into csv data files.
     * 
     * @return Returns a string that is used to write into a csv data file.
     */
    public String toCSVString() {
        return getName() + "," + getPhone() + "," + getAid() + "," + getQuantity();
    }

    /**
     * Donation matching to assign Donor's donation with an NGO's item request.
     * 
     * @param aid             Aid object which has details of the aid item.
     * @param requestListTemp List of requests made by NGOs
     * @param aidCompleted    List of all donations made
     */
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
}
