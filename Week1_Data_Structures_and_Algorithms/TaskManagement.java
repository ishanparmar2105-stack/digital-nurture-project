/*
 * Exercise 5: Task Management System
 * 
 * Linked Lists:
 * - Singly Linked List: Each node points to the next node. Traversal in one direction.
 * - Doubly Linked List: Each node points to both next and previous. Traversal in both directions.
 * 
 * Time Complexity:
 * - Add (head):    O(1)
 * - Add (tail):    O(n) for singly, O(1) for doubly with tail pointer
 * - Search:        O(n)
 * - Traverse:      O(n)
 * - Delete (head): O(1)
 * - Delete (mid):  O(n)
 * 
 * Advantages over arrays:
 * - Dynamic size (no pre-allocation needed)
 * - Efficient insertions/deletions (no shifting required)
 * - No wasted memory from unused capacity
 */

class Task {
    int taskId;
    String taskName;
    String status;
    Task next;

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
        this.next = null;
    }
    public String toString() {
        return String.format("Task #%d | %-15s | %s", taskId, taskName, status);
    }
}

class TaskLinkedList {
    private Task head;

    // Add task at the end - O(n)
    public void addTask(Task task) {
        if (head == null) { head = task; }
        else {
            Task current = head;
            while (current.next != null) current = current.next;
            current.next = task;
        }
        System.out.println("Added: " + task);
    }

    // Search task by ID - O(n)
    public Task searchTask(int taskId) {
        Task current = head;
        while (current != null) {
            if (current.taskId == taskId) return current;
            current = current.next;
        }
        return null;
    }

    // Traverse all tasks - O(n)
    public void traverseTasks() {
        System.out.println("\n--- Task List ---");
        Task current = head;
        if (current == null) { System.out.println("No tasks."); return; }
        while (current != null) {
            System.out.println(current);
            current = current.next;
        }
        System.out.println("-----------------\n");
    }

    // Delete task by ID - O(n)
    public void deleteTask(int taskId) {
        if (head == null) { System.out.println("List is empty."); return; }
        if (head.taskId == taskId) {
            System.out.println("Deleted: " + head);
            head = head.next;
            return;
        }
        Task current = head;
        while (current.next != null) {
            if (current.next.taskId == taskId) {
                System.out.println("Deleted: " + current.next);
                current.next = current.next.next;
                return;
            }
            current = current.next;
        }
        System.out.println("Task ID " + taskId + " not found.");
    }
}

public class TaskManagement {
    public static void main(String[] args) {
        System.out.println("=== Task Management System (Linked List) ===\n");
        TaskLinkedList taskList = new TaskLinkedList();
        taskList.addTask(new Task(1, "Design UI", "In Progress"));
        taskList.addTask(new Task(2, "Write API", "Pending"));
        taskList.addTask(new Task(3, "Unit Tests", "Pending"));
        taskList.addTask(new Task(4, "Deploy", "Not Started"));
        taskList.traverseTasks();

        System.out.println("Searching for Task #3:");
        Task found = taskList.searchTask(3);
        System.out.println(found != null ? "Found: " + found : "Not found.");

        taskList.deleteTask(2);
        taskList.traverseTasks();
    }
}
