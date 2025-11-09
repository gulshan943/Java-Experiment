import java.sql.*;
import java.util.*;

// ===== MODEL =====
class Student {
    private int id;
    private String name;
    private double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getMarks() { return marks; }

    @Override
    public String toString() {
        return id + " | " + name + " | " + marks;
    }
}

// ===== DAO (Data Access Object) =====
class StudentDAO {
    private Connection con;

    public StudentDAO(Connection con) {
        this.con = con;
    }

    public void addStudent(Student s) throws SQLException {
        String sql = "INSERT INTO student (name, marks) VALUES (?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getName());
            ps.setDouble(2, s.getMarks());
            ps.executeUpdate();
        }
    }

    public List<Student> getAllStudents() throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM student";
        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Student(rs.getInt("id"), rs.getString("name"), rs.getDouble("marks")));
            }
        }
        return list;
    }
}

// ===== CONTROLLER =====
class StudentController {
    private StudentDAO dao;

    public StudentController(StudentDAO dao) {
        this.dao = dao;
    }

    public void addStudent(String name, double marks) throws SQLException {
        dao.addStudent(new Student(0, name, marks));
    }

    public void displayAll() throws SQLException {
        List<Student> list = dao.getAllStudents();
        list.forEach(System.out::println);
    }
}

// ===== VIEW (Main Application) =====
public class StudentMVCApp {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/your_database";
        String user = "root";
        String password = "your_password";

        try (Connection con = DriverManager.getConnection(url, user, password);
             Scanner sc = new Scanner(System.in)) {

            Class.forName("com.mysql.cj.jdbc.Driver");
            StudentDAO dao = new StudentDAO(con);
            StudentController controller = new StudentController(dao);

            while (true) {
                System.out.println("\n=== STUDENT MENU ===");
                System.out.println("1. Add Student");
                System.out.println("2. View All Students");
                System.out.println("3. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter name: ");
                        String name = sc.next();
                        System.out.print("Enter marks: ");
                        double marks = sc.nextDouble();
                        controller.addStudent(name, marks);
                        System.out.println("Student added!");
                    }
                    case 2 -> controller.displayAll();
                    case 3 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
