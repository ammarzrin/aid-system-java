import java.util.Scanner;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;

class Information {
    private String username;
    private String password;
    int phoneNumber;

    public Information() {
    }

    public Information(String username, String password, int phoneNumber) {

        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setphoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public int getphoneNumber() {
        return this.phoneNumber;
    }

}

public class AssignmentTest {

    Information a = new Information();
    Scanner s = new Scanner(System.in);

    public AssignmentTest() {
        try {
            System.out.println("-----------------------");
            System.out.println("1.  Create user account");
            System.out.println("2.  Login user account");
            System.out.println("-----------------------");
            System.out.print("Enter choice : ");
            int choice = s.nextInt();
            if (choice == 1) {
                createaccount();
            } else if (choice == 2) {
                login();
            } else {
                System.out.println("Invalid choice!");
                new AssignmentTest();
            }

        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
    }

    void login() {
        try {
            InputStream input = new FileInputStream("AssignmentTest.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            System.out.println("\nL O G I N\n");
            System.out.print("Enter username :");
            a.setUsername(s.next());
            System.out.print("Enter password :");
            a.setPassword(s.next());
            String _temp = null;
            String _user;
            String _pass;
            boolean found = false;
            while ((_temp = reader.readLine()) != null) {
                String[] account = _temp.split(",");
                _user = account[0];
                _pass = account[1];
                if (_user.equals(a.getUsername()) && _pass.equals(a.getPassword())) {
                    found = true;
                }
            }
            if (found == true) {
                System.out.print("Access granted!");
            } else {
                System.out.println("Access denied! Invalid username or password!");
            }
            reader.close();
            new AssignmentTest();
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }

    }

    void createaccount() {
        try {
            OutputStream output = new BufferedOutputStream(
                    new FileOutputStream("AssignmentTest.txt"));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
            System.out.println("\nC R E A T E  A C C O U N T \n");
            System.out.print("Enter username :");
            a.setUsername(s.next());
            System.out.print("Enter Password :");
            a.setPassword(s.next());
            System.out.print("Enter phone number  : ");
            a.setphoneNumber(s.nextInt());

            writer.write(a.getUsername() + "," + a.getPassword() + "," + "0" + a.getphoneNumber());
            writer.newLine();
            System.out.println("Account has been successfully saved");
            writer.close();
            output.close();

            new AssignmentTest();
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }

    }

    public static void main(String[] args) {
        new AssignmentTest();
    }
}