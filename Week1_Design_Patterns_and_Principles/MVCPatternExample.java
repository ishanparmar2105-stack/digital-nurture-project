/*
 * Exercise 10: Implementing the MVC Pattern
 * 
 * Scenario: Simple web application for managing student records using MVC pattern.
 */

// Model class
class Student {
    private String name;
    private String id;
    private String grade;

    public Student(String id, String name, String grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }
}

// View class
class StudentView {
    public void displayStudentDetails(String studentName, String studentId, String studentGrade) {
        System.out.println("=== Student Details ===");
        System.out.println("Name:  " + studentName);
        System.out.println("ID:    " + studentId);
        System.out.println("Grade: " + studentGrade);
        System.out.println("=======================");
    }
}

// Controller class
class StudentController {
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }
    public void setStudentName(String name) { model.setName(name); }
    public String getStudentName() { return model.getName(); }
    public void setStudentId(String id) { model.setId(id); }
    public String getStudentId() { return model.getId(); }
    public void setStudentGrade(String grade) { model.setGrade(grade); }
    public String getStudentGrade() { return model.getGrade(); }
    public void updateView() {
        view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
    }
}

// Test class
public class MVCPatternExample {
    public static void main(String[] args) {
        System.out.println("=== MVC Pattern Example ===\n");
        Student student = new Student("S001", "Ishan Parmar", "A");
        StudentView view = new StudentView();
        StudentController controller = new StudentController(student, view);
        System.out.println("Initial Student Details:");
        controller.updateView();
        System.out.println("\nUpdating student details...");
        controller.setStudentName("Ishan P.");
        controller.setStudentGrade("A+");
        System.out.println("\nUpdated Student Details:");
        controller.updateView();
    }
}
