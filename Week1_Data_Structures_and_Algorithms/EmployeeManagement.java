/*
 * Exercise 4: Employee Management System
 * 
 * Arrays in Memory:
 * - Stored in contiguous memory locations
 * - Fast access via index: O(1)
 * - Fixed size (must define capacity at creation)
 * 
 * Time Complexity:
 * - Add:      O(1) if space available, O(n) if array is full (need to resize)
 * - Search:   O(n) linear search, O(log n) binary search if sorted
 * - Traverse: O(n)
 * - Delete:   O(n) (need to shift elements)
 * 
 * Limitations: Fixed size, costly insertions/deletions in the middle.
 * Use when: size is known, frequent random access needed.
 */

class Employee {
    private int employeeId;
    private String name;
    private String position;
    private double salary;

    public Employee(int id, String name, String position, double salary) {
        this.employeeId = id;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }
    public int getEmployeeId() { return employeeId; }
    public String toString() {
        return String.format("ID: %d | %-15s | %-12s | $%.2f", employeeId, name, position, salary);
    }
}

class EmployeeManagementSystem {
    private Employee[] employees;
    private int size;

    public EmployeeManagementSystem(int capacity) {
        employees = new Employee[capacity];
        size = 0;
    }

    // Add - O(1) average
    public void addEmployee(Employee emp) {
        if (size >= employees.length) {
            System.out.println("Array is full! Cannot add more employees.");
            return;
        }
        employees[size++] = emp;
        System.out.println("Added: " + emp);
    }

    // Search - O(n) linear search
    public Employee searchEmployee(int id) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == id) return employees[i];
        }
        return null;
    }

    // Traverse - O(n)
    public void traverseEmployees() {
        System.out.println("\n--- All Employees ---");
        for (int i = 0; i < size; i++) System.out.println(employees[i]);
        System.out.println("---------------------\n");
    }

    // Delete - O(n) due to shifting
    public void deleteEmployee(int id) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == id) {
                System.out.println("Deleted: " + employees[i]);
                for (int j = i; j < size - 1; j++) employees[j] = employees[j + 1];
                employees[--size] = null;
                return;
            }
        }
        System.out.println("Employee ID " + id + " not found.");
    }
}

public class EmployeeManagement {
    public static void main(String[] args) {
        System.out.println("=== Employee Management System ===\n");
        EmployeeManagementSystem ems = new EmployeeManagementSystem(10);
        ems.addEmployee(new Employee(1, "Alice Johnson", "Manager", 70000));
        ems.addEmployee(new Employee(2, "Bob Brown", "Developer", 60000));
        ems.addEmployee(new Employee(3, "Charlie Davis", "Designer", 55000));
        ems.addEmployee(new Employee(4, "Diana Wilson", "Analyst", 52000));
        ems.traverseEmployees();

        System.out.println("Searching for Employee ID 3:");
        Employee found = ems.searchEmployee(3);
        System.out.println(found != null ? "Found: " + found : "Not found.");

        ems.deleteEmployee(2);
        ems.traverseEmployees();
    }
}
