import java.util.*;

public class AutoBoxingSum {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> numbers = new ArrayList<>();

        System.out.print("Enter integers separated by spaces: ");
        String input = sc.nextLine();
        String[] parts = input.split(" ");

        // Autoboxing: converting int → Integer automatically
        for (String part : parts) {
            int num = Integer.parseInt(part); // parsing string → int
            numbers.add(num); // autoboxing happens here
        }

        int sum = 0;
        // Unboxing: converting Integer → int automatically
        for (Integer num : numbers) {
            sum += num; // unboxing happens here
        }

        System.out.println("Sum of integers: " + sum);
        sc.close();
    }
}
