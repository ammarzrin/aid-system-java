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

    public static void menu() {
        displayHeader("NGO Main Menu");
        System.out.println("Select an option:");
        System.out.println("1. Make a Request!");
        System.out.println("2. View My Requests");
        System.out.println("3. View Received Donations");
        System.out.println("4. View All Donations");
        System.out.println("5. Log Out");
        System.out.println("6. Exit Program");
        ngoActions(getMenuInput());
    }

    private static void ngoActions(int choice) {
        switch (choice) {
            case 1:
                System.out.println("Let's make a request.");
                break;
            case 2:
                System.out.println("What have I requested?");
                break;
            case 3:
                System.out.println("What donations have my NGO received?");
                break;
            case 4:
                System.out.println("Let's view all donations made so far.");
                break;
            case 5:
                System.out.println("Log out!");
                break;
            case 6:
                System.out.println("Exiting the program...");
                System.exit(0);
                break;
        }
    }
}
