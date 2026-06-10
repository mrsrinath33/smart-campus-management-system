import java.sql.*;
import java.util.Scanner;

public class Login {

    public static boolean loginSystem() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Username: ");
        String username = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        try {
            Connection con = DBConnection.getConnection();

            if (con == null) {
                System.out.println("Unable to connect to database. Please check DB configuration and JDBC driver.");
                return false;
            }

            String query = "SELECT * FROM users WHERE username=? AND password=?";

            try (PreparedStatement pst = con.prepareStatement(query)) {
                pst.setString(1, username);
                pst.setString(2, password);

                try (ResultSet rs = pst.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("Login Successful");
                        return true;
                    } else {
                        System.out.println("Invalid Credentials");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }

        return false;
    }
}