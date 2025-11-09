import java.sql.*;
import java.util.*;

public class ProductCRUDApp {

    static final String URL = "jdbc:mysql://localhost:3306/your_database";
    static final String USER = "root";
    static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Database connected successfully!");

            while (true) {
                System.out.println("\n===== PRODUCT MENU =====");
                System.out.println("1. Add Product");
                System.out.println("2. View Products");
                System.out.println("3. Update Product");
                System.out.println("4. Delete Product");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> addProduct(con, sc);
                    case 2 -> viewProducts(con);
                    case 3 -> updateProduct(con, sc);
                    case 4 -> deleteProduct(con, sc);
                    case 5 -> { 
                        System.out.println("Exiting...");
                        sc.close();
                        return;
                    }
                    default -> System.out.println("Invalid choice!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void addProduct(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter product name: ");
        String name = sc.next();
        System.out.print("Enter price: ");
        double price = sc.nextDouble();

        String sql = "INSERT INTO product(name, price) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setDouble(2, price);
            int rows = ps.executeUpdate();
            System.out.println(rows + " product added.");
        }
    }

    static void viewProducts(Connection con) throws SQLException {
        String sql = "SELECT * FROM product";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nProduct List:");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                                   rs.getString("name") + " | " +
                                   rs.getDouble("price"));
            }
        }
    }

    static void updateProduct(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter product ID to update: ");
        int id = sc.nextInt();
        System.out.print("Enter new price: ");
        double price = sc.nextDouble();

        String sql = "UPDATE product SET price=? WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDouble(1, price);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            System.out.println(rows + " product updated.");
        }
    }

    static void deleteProduct(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter product ID to delete: ");
        int id = sc.nextInt();

        String sql = "DELETE FROM product WHERE id=?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println(rows + " product deleted.");
        }
    }
}
