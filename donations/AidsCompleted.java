package donations;

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

}
