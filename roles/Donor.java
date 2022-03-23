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

    public void menu() {
    }

    public void login() {
    }

    public void createAccount() {
    }
}