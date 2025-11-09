import java.util.*;
import java.util.stream.*;

class Product {
    private String name;
    private String category;
    private double price;

    public Product(String name, String category, double price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " (" + category + ", " + price + ")";
    }
}

public class Main {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
            new Product("Laptop", "Electronics", 80000),
            new Product("Phone", "Electronics", 60000),
            new Product("Shirt", "Clothing", 2000),
            new Product("Jeans", "Clothing", 3500),
            new Product("Mixer", "Home", 5000),
            new Product("Fan", "Home", 2500)
        );

        // 1️⃣ Group by category
        System.out.println("Products grouped by category:");
        Map<String, List<Product>> grouped = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));
        grouped.forEach((category, list) -> {
            System.out.println(category + ": " + list);
        });

        // 2️⃣ Find max price product
        products.stream()
                .max(Comparator.comparing(Product::getPrice))
                .ifPresent(p -> System.out.println("\nMost expensive product: " + p));

        // 3️⃣ Average price per category
        System.out.println("\nAverage price per category:");
        Map<String, Double> avgPrice = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.averagingDouble(Product::getPrice)));
        avgPrice.forEach((category, avg) ->
            System.out.println(category + ": " + avg)
        );
    }
}
