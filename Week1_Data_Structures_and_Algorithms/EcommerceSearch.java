/*
 * Exercise 2: E-commerce Platform Search Function
 * 
 * Big O Notation: Describes the upper bound of time complexity.
 * - O(1): Constant time (HashMap lookup)
 * - O(log n): Logarithmic (Binary Search)
 * - O(n): Linear (Linear Search)
 * - O(n log n): Linearithmic (Merge Sort)
 * - O(n²): Quadratic (Bubble Sort)
 * 
 * Linear Search: Best O(1), Average O(n), Worst O(n) - works on unsorted data
 * Binary Search: Best O(1), Average O(log n), Worst O(log n) - requires sorted data
 * 
 * Binary search is more suitable for e-commerce as product catalogs can be
 * sorted once and searched many times, giving O(log n) per search.
 */

import java.util.Arrays;
import java.util.Comparator;

class Product {
    private int productId;
    private String productName;
    private String category;

    public Product(int productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }
    public int getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }
    public String toString() {
        return "ID: " + productId + " | " + productName + " | " + category;
    }
}

public class EcommerceSearch {
    // Linear Search - O(n) time complexity
    public static Product linearSearch(Product[] products, String targetName) {
        for (Product p : products) {
            if (p.getProductName().equalsIgnoreCase(targetName)) return p;
        }
        return null;
    }

    // Binary Search - O(log n) time complexity (requires sorted array)
    public static Product binarySearch(Product[] products, String targetName) {
        int low = 0, high = products.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = products[mid].getProductName().compareToIgnoreCase(targetName);
            if (cmp == 0) return products[mid];
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("=== E-commerce Platform Search ===\n");

        Product[] products = {
            new Product(1, "Laptop", "Electronics"),
            new Product(2, "Headphones", "Electronics"),
            new Product(3, "Shirt", "Clothing"),
            new Product(4, "Book", "Education"),
            new Product(5, "Watch", "Accessories"),
            new Product(6, "Shoes", "Clothing"),
            new Product(7, "Camera", "Electronics")
        };

        // Linear Search
        System.out.println("--- Linear Search ---");
        long start = System.nanoTime();
        Product result1 = linearSearch(products, "Watch");
        long end = System.nanoTime();
        System.out.println("Found: " + result1);
        System.out.println("Time: " + (end - start) + " ns\n");

        // Sort for binary search
        Arrays.sort(products, Comparator.comparing(Product::getProductName));
        System.out.println("Sorted Products:");
        for (Product p : products) System.out.println("  " + p);

        // Binary Search
        System.out.println("\n--- Binary Search ---");
        start = System.nanoTime();
        Product result2 = binarySearch(products, "Watch");
        end = System.nanoTime();
        System.out.println("Found: " + result2);
        System.out.println("Time: " + (end - start) + " ns");

        System.out.println("\n--- Comparison ---");
        System.out.println("Linear Search: O(n) - Best for small/unsorted datasets");
        System.out.println("Binary Search: O(log n) - Best for large/sorted datasets");
    }
}
