/*
 * Exercise 3: Sorting Customer Orders
 * 
 * Sorting Algorithms:
 * - Bubble Sort: O(n²) - simple, swaps adjacent elements, stable
 * - Insertion Sort: O(n²) - good for nearly sorted data, stable
 * - Quick Sort: O(n log n) average, O(n²) worst - fast, in-place, unstable
 * - Merge Sort: O(n log n) always - stable, needs extra space
 * 
 * Quick Sort is preferred over Bubble Sort because:
 * 1. Average case O(n log n) vs O(n²)
 * 2. In-place sorting (low memory overhead)
 * 3. Cache-friendly due to sequential memory access
 */

class Order {
    private int orderId;
    private String customerName;
    private double totalPrice;

    public Order(int orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }
    public double getTotalPrice() { return totalPrice; }
    public String toString() {
        return String.format("Order #%d | %-12s | $%.2f", orderId, customerName, totalPrice);
    }
}

public class SortingCustomerOrders {

    // Bubble Sort - O(n²) time complexity
    public static void bubbleSort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }

    // Quick Sort - O(n log n) average time complexity
    public static void quickSort(Order[] orders, int low, int high) {
        if (low < high) {
            int pi = partition(orders, low, high);
            quickSort(orders, low, pi - 1);
            quickSort(orders, pi + 1, high);
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }

    static void printOrders(Order[] orders) {
        for (Order o : orders) System.out.println("  " + o);
    }

    public static void main(String[] args) {
        System.out.println("=== Sorting Customer Orders ===\n");

        Order[] orders1 = {
            new Order(1, "Alice", 250.00),
            new Order(2, "Bob", 150.75),
            new Order(3, "Charlie", 500.50),
            new Order(4, "Diana", 75.25),
            new Order(5, "Eve", 320.00)
        };
        Order[] orders2 = orders1.clone();

        System.out.println("Original Orders:");
        printOrders(orders1);

        long start = System.nanoTime();
        bubbleSort(orders1);
        long bubbleTime = System.nanoTime() - start;
        System.out.println("\nAfter Bubble Sort (O(n²)):");
        printOrders(orders1);
        System.out.println("Time: " + bubbleTime + " ns");

        start = System.nanoTime();
        quickSort(orders2, 0, orders2.length - 1);
        long quickTime = System.nanoTime() - start;
        System.out.println("\nAfter Quick Sort (O(n log n)):");
        printOrders(orders2);
        System.out.println("Time: " + quickTime + " ns");

        System.out.println("\n--- Performance Comparison ---");
        System.out.println("Bubble Sort: O(n²) - Simple but slow for large datasets");
        System.out.println("Quick Sort:  O(n log n) avg - Much faster, preferred in practice");
    }
}
