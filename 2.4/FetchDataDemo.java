import java.sql.*;

public class FetchDataDemo {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/your_database"; // replace with your DB name
        String user = "root"; // replace with your username
        String password = "your_password"; // replace with your password

        try {
            // 1. Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. Establish connection
            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to Database!");

            // 3. Execute query
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM employee"); // change table name as needed

            // 4. Process result
            System.out.println("\nEmployee Table Data:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                                   ", Name: " + rs.getString("name") +
                                   ", Salary: " + rs.getDouble("salary"));
            }

            // 5. Close connection
            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
