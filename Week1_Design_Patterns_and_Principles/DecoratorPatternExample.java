/*
 * Exercise 5: Implementing the Decorator Pattern
 * 
 * Scenario: Developing a notification system where notifications can be sent 
 * via multiple channels (Email, SMS, Slack). Use the Decorator Pattern to 
 * add functionalities dynamically.
 * 
 * The Decorator Pattern attaches additional responsibilities to an object dynamically.
 * Decorators provide a flexible alternative to subclassing for extending functionality.
 */

// Component Interface
interface Notifier {
    void send(String message);
}

// Concrete Component: EmailNotifier
class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("[Email] Sending: " + message);
    }
}

// Abstract Decorator class
abstract class NotifierDecorator implements Notifier {
    protected Notifier wrappedNotifier;
    
    public NotifierDecorator(Notifier notifier) {
        this.wrappedNotifier = notifier;
    }
    
    @Override
    public void send(String message) {
        wrappedNotifier.send(message);
    }
}

// Concrete Decorator: SMSNotifierDecorator
class SMSNotifierDecorator extends NotifierDecorator {
    public SMSNotifierDecorator(Notifier notifier) {
        super(notifier);
    }
    
    @Override
    public void send(String message) {
        super.send(message);  // Send via wrapped notifier first
        sendSMS(message);     // Then send via SMS
    }
    
    private void sendSMS(String message) {
        System.out.println("[SMS] Sending: " + message);
    }
}

// Concrete Decorator: SlackNotifierDecorator
class SlackNotifierDecorator extends NotifierDecorator {
    public SlackNotifierDecorator(Notifier notifier) {
        super(notifier);
    }
    
    @Override
    public void send(String message) {
        super.send(message);     // Send via wrapped notifier first
        sendSlack(message);       // Then send via Slack
    }
    
    private void sendSlack(String message) {
        System.out.println("[Slack] Sending: " + message);
    }
}

// Test class
public class DecoratorPatternExample {
    public static void main(String[] args) {
        System.out.println("=== Decorator Pattern Example ===\n");
        
        // Send via Email only
        System.out.println("--- Email Only ---");
        Notifier emailNotifier = new EmailNotifier();
        emailNotifier.send("Hello, this is an email notification!");
        
        System.out.println();
        
        // Send via Email + SMS
        System.out.println("--- Email + SMS ---");
        Notifier emailAndSMS = new SMSNotifierDecorator(new EmailNotifier());
        emailAndSMS.send("Hello, this is a multi-channel notification!");
        
        System.out.println();
        
        // Send via Email + SMS + Slack
        System.out.println("--- Email + SMS + Slack ---");
        Notifier allChannels = new SlackNotifierDecorator(
                                    new SMSNotifierDecorator(
                                        new EmailNotifier()));
        allChannels.send("Hello, this goes to all channels!");
    }
}
