// PIC: MOHD FATIH FIKRI BIN MASIARA (1201302166)
// Donor role
package roles;

public class Donor extends Account {
    public String name;
    public String phone;

    public Donor() {
    }

    public Donor(String username, String password, String name, String phone) {
        super(username, password);
        this.name = name;
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return this.phone;
    }

    public static void menu(Donor donor) {
        displayHeader("Donor Main Menu");
        System.out.println("Select an option:");
        System.out.println("1. Make a Donation!");
        System.out.println("2. View My Donations");
        System.out.println("3. View NGO Aid Requests");
        System.out.println("4. View All Donations");
        System.out.println("5. Log Out");
        System.out.println("6. Exit Program");
        donorActions(getMenuInput(), donor);
    }

    private static void donorActions(int choice, Donor donor) {
        switch (choice) {
            case 1:
                donatingForm(donor);
                break;
            case 2:
                viewPersonalDonations(donor);
                break;
            case 3:
                viewNGOrequests();
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

    private static void donatingForm(Donor donor) {
        System.out.println("Let's make a donation.");
    }

    private static void viewPersonalDonations(Donor donor) {
        System.out.println("What have I donated?");
        System.out.println(donor.getName());

    }

    private static void viewNGOrequests() {
        System.out.println("What are NGOs requesting?");
    }

}