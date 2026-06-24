/*
 * Exercise 1: Implementing the Singleton Pattern
 * 
 * Scenario: Ensure that a logging utility class has only one instance 
 * throughout the application lifecycle to ensure consistent logging.
 * 
 * The Singleton Pattern restricts the instantiation of a class to a single instance 
 * and provides a global point of access to that instance. This is useful when exactly 
 * one object is needed to coordinate actions across the system.
 */

// Logger class implementing the Singleton Pattern
class Logger {
    // Private static instance of the Logger class
    private static Logger instance;
    
    // Private constructor to prevent instantiation from outside
    private Logger() {
        System.out.println("Logger instance created.");
    }
    
    // Public static method to get the single instance of Logger (Thread-safe with lazy initialization)
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }
    
    // Method to log messages
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}

// Test class to verify Singleton implementation
public class SingletonPatternExample {
    public static void main(String[] args) {
        System.out.println("=== Singleton Pattern Example ===\n");
        
        // Get the first instance of Logger
        Logger logger1 = Logger.getInstance();
        logger1.log("This is the first log message.");
        
        // Get the second instance of Logger
        Logger logger2 = Logger.getInstance();
        logger2.log("This is the second log message.");
        
        // Verify both references point to the same instance
        System.out.println("\nlogger1 hashCode: " + logger1.hashCode());
        System.out.println("logger2 hashCode: " + logger2.hashCode());
        System.out.println("Are both instances the same? " + (logger1 == logger2));
    }
}
