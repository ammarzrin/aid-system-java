// PIC: Muhammad Ammar bin Muhamad Azrin (1191102915)
// NGO role
package roles;

public class NGO extends Account {
    public String ngoname;
    public int manpower;

    public NGO() {
    }

    public NGO(String username, String password, String ngoname, int manpower) {
        super(username, password);
        this.ngoname = ngoname;
        this.manpower = manpower;
    }

    public void setNGO(String ngoname) {
        this.ngoname = ngoname;
    }

    public void setManpower(int manpower) {
        this.manpower = manpower;
    }

    public String getNGO() {
        return this.ngoname;
    }

    public int getManpower() {
        return this.manpower;
    }

    public static void menu(NGO ngo) {
        displayHeader("NGO Main Menu");
        System.out.println("Select an option:");
        System.out.println("1. Make a Request!");
        System.out.println("2. View My Requests");
        System.out.println("3. View Received Donations");
        System.out.println("4. View All Donations");
        System.out.println("5. Log Out");
        System.out.println("6. Exit Program");
        ngoActions(getMenuInput(), ngo);
    }

    private static void ngoActions(int choice, NGO ngo) {
        switch (choice) {
            case 1:
                requestingForm(ngo);
                break;
            case 2:
                viewMyRequests(ngo);
                break;
            case 3:
                viewReceivedAids(ngo);
                break;
            case 4:
                viewAllDonations();
                break;
            case 5:
                logout();
                break;
            case 6:
                System.out.println("Exiting the program...");
                System.out.println("Goodbye!");
                System.exit(0);
                break;
        }
    }

    private static void requestingForm(NGO ngo) {
        System.out.println("Let's make a request.");
    }

    private static void viewMyRequests(NGO ngo) {
        System.out.println("What have I requested?");

    }

    private static void viewReceivedAids(NGO ngo) {
        System.out.println("What donations have my NGO received?");
    }
}
