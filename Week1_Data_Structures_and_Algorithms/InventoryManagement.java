/*
 * Exercise 1: Inventory Management System
 * 
 * Scenario: Efficient data storage and retrieval for a warehouse inventory.
 * 
 * Why data structures are essential:
 * - Large inventories require efficient lookups, insertions, and deletions.
 * - HashMap provides O(1) average-case for get/put operations vs O(n) for ArrayList search.
 * - Choosing the right data structure reduces time complexity significantly.
 * 
 * Data structure chosen: HashMap (productId as key for O(1) lookups)
 * 
 * Time Complexity Analysis:
 * - Add:    O(1) average (HashMap.put)
 * - Update: O(1) average (HashMap.put after get)
 * - Delete: O(1) average (HashMap.remove)
 * - Search: O(1) average (HashMap.get)
 * 
 * Optimization: HashMap provides the best average-case complexity.
 * For ordered traversal, TreeMap (O(log n)) could be used instead.
 */

import java.util.HashMap;
import java.util.Map;

class Product {
    private int productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(int productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }
    public int getProductId() { return productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String toString() {
        return String.format("ID: %d | Name: %-15s | Qty: %4d | Price: $%.2f", productId, productName, quantity, price);
    }
}

class Inventory {
    private HashMap<Integer, Product> products = new HashMap<>();

    // Add product - O(1) average
    public void addProduct(Product product) {
        if (products.containsKey(product.getProductId())) {
            System.out.println("Product ID " + product.getProductId() + " already exists!");
            return;
        }
        products.put(product.getProductId(), product);
        System.out.println("Added: " + product);
    }

    // Update product - O(1) average
    public void updateProduct(int productId, String name, int quantity, double price) {
        Product p = products.get(productId);
        if (p == null) { System.out.println("Product ID " + productId + " not found!"); return; }
        p.setProductName(name);
        p.setQuantity(quantity);
        p.setPrice(price);
        System.out.println("Updated: " + p);
    }

    // Delete product - O(1) average
    public void deleteProduct(int productId) {
        Product removed = products.remove(productId);
        if (removed != null) System.out.println("Deleted: " + removed);
        else System.out.println("Product ID " + productId + " not found!");
    }

    // Display all products - O(n)
    public void displayAll() {
        System.out.println("\n--- Current Inventory ---");
        if (products.isEmpty()) { System.out.println("Inventory is empty."); return; }
        for (Map.Entry<Integer, Product> entry : products.entrySet()) {
            System.out.println(entry.getValue());
        }
        System.out.println("-------------------------\n");
    }
}

public class InventoryManagement {
    public static void main(String[] args) {
        System.out.println("=== Inventory Management System ===\n");
        Inventory inventory = new Inventory();
        inventory.addProduct(new Product(101, "Laptop", 50, 999.99));
        inventory.addProduct(new Product(102, "Mouse", 200, 29.99));
        inventory.addProduct(new Product(103, "Keyboard", 150, 49.99));
        inventory.addProduct(new Product(104, "Monitor", 75, 299.99));
        inventory.displayAll();
        inventory.updateProduct(102, "Wireless Mouse", 180, 39.99);
        inventory.deleteProduct(104);
        inventory.displayAll();
    }
}
