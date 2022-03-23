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

    public void menu() {
    }

    public void login() {
    }

    public void createAccount() {
    }
}
