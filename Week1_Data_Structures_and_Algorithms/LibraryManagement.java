/*
 * Exercise 6: Library Management System
 * 
 * Linear Search: O(n) - scans each element sequentially. Works on unsorted data.
 * Binary Search: O(log n) - halves search space each step. Requires sorted data.
 * 
 * When to use each:
 * - Linear: Small datasets, unsorted data, or when sorting cost > search benefit
 * - Binary: Large datasets that are already sorted or searched frequently
 */

import java.util.Arrays;
import java.util.Comparator;

class Book {
    private int bookId;
    private String title;
    private String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }
    public String getTitle() { return title; }
    public String toString() {
        return String.format("ID: %d | %-25s | %s", bookId, title, author);
    }
}

public class LibraryManagement {

    // Linear Search - O(n)
    public static Book linearSearchByTitle(Book[] books, String title) {
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) return b;
        }
        return null;
    }

    // Binary Search - O(log n) - requires sorted array by title
    public static Book binarySearchByTitle(Book[] books, String title) {
        int low = 0, high = books.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = books[mid].getTitle().compareToIgnoreCase(title);
            if (cmp == 0) return books[mid];
            else if (cmp < 0) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("=== Library Management System ===\n");

        Book[] books = {
            new Book(1, "The Great Gatsby", "F. Scott Fitzgerald"),
            new Book(2, "To Kill a Mockingbird", "Harper Lee"),
            new Book(3, "1984", "George Orwell"),
            new Book(4, "Pride and Prejudice", "Jane Austen"),
            new Book(5, "The Catcher in the Rye", "J.D. Salinger"),
            new Book(6, "Moby Dick", "Herman Melville")
        };

        // Linear Search
        System.out.println("--- Linear Search ---");
        Book result = linearSearchByTitle(books, "1984");
        System.out.println(result != null ? "Found: " + result : "Not found");

        // Sort for binary search
        Arrays.sort(books, Comparator.comparing(Book::getTitle));
        System.out.println("\nSorted Books:");
        for (Book b : books) System.out.println("  " + b);

        // Binary Search
        System.out.println("\n--- Binary Search ---");
        result = binarySearchByTitle(books, "1984");
        System.out.println(result != null ? "Found: " + result : "Not found");

        System.out.println("\n--- Comparison ---");
        System.out.println("Linear: O(n) - good for small/unsorted collections");
        System.out.println("Binary: O(log n) - ideal for large sorted collections");
    }
}
