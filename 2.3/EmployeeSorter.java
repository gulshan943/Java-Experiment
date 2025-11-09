import java.util.*;

class Employee {
    private String name;
    private int age;
    private double salary;

    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public double getSalary() { return salary; }

    @Override
    public String toString() {
        return name + " (Age: " + age + ", Salary: " + salary + ")";
    }
}

public class EmployeeSorter {
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Nitin", 23, 55000),
            new Employee("Riya", 22, 60000),
            new Employee("Aman", 25, 50000)
        );

        System.out.println("Sort by Name:");
        employees.stream()
                 .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
                 .forEach(System.out::println);

        System.out.println("\nSort by Age:");
        employees.stream()
                 .sorted((e1, e2) -> Integer.compare(e1.getAge(), e2.getAge()))
                 .forEach(System.out::println);

        System.out.println("\nSort by Salary:");
        employees.stream()
                 .sorted((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()))
                 .forEach(System.out::println);
    }
}
