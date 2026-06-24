/*
 * Exercise 4: Implementing the Adapter Pattern
 * 
 * Scenario: Developing a payment processing system that needs to integrate 
 * with multiple third-party payment gateways with different interfaces.
 * 
 * The Adapter Pattern converts the interface of a class into another interface 
 * that the client expects. It lets classes work together that couldn't otherwise 
 * because of incompatible interfaces.
 */

// Target Interface
interface PaymentProcessor {
    void processPayment(double amount);
}

// Adaptee Class 1: PayPal Gateway (third-party with different interface)
class PayPalGateway {
    public void makePayment(double amount) {
        System.out.println("Processing payment of $" + amount + " through PayPal.");
    }
}

// Adaptee Class 2: Stripe Gateway (third-party with different interface)
class StripeGateway {
    public void pay(double amount) {
        System.out.println("Processing payment of $" + amount + " through Stripe.");
    }
}

// Adaptee Class 3: Square Gateway (third-party with different interface)
class SquareGateway {
    public void chargeAmount(double amount) {
        System.out.println("Processing payment of $" + amount + " through Square.");
    }
}

// Adapter for PayPal
class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway payPalGateway;
    
    public PayPalAdapter(PayPalGateway payPalGateway) {
        this.payPalGateway = payPalGateway;
    }
    
    @Override
    public void processPayment(double amount) {
        payPalGateway.makePayment(amount);
    }
}

// Adapter for Stripe
class StripeAdapter implements PaymentProcessor {
    private StripeGateway stripeGateway;
    
    public StripeAdapter(StripeGateway stripeGateway) {
        this.stripeGateway = stripeGateway;
    }
    
    @Override
    public void processPayment(double amount) {
        stripeGateway.pay(amount);
    }
}

// Adapter for Square
class SquareAdapter implements PaymentProcessor {
    private SquareGateway squareGateway;
    
    public SquareAdapter(SquareGateway squareGateway) {
        this.squareGateway = squareGateway;
    }
    
    @Override
    public void processPayment(double amount) {
        squareGateway.chargeAmount(amount);
    }
}

// Test class
public class AdapterPatternExample {
    public static void main(String[] args) {
        System.out.println("=== Adapter Pattern Example ===\n");
        
        // Using PayPal through adapter
        PaymentProcessor paypalProcessor = new PayPalAdapter(new PayPalGateway());
        paypalProcessor.processPayment(100.00);
        
        // Using Stripe through adapter
        PaymentProcessor stripeProcessor = new StripeAdapter(new StripeGateway());
        stripeProcessor.processPayment(250.50);
        
        // Using Square through adapter
        PaymentProcessor squareProcessor = new SquareAdapter(new SquareGateway());
        squareProcessor.processPayment(75.25);
        
        System.out.println("\nAll payment gateways processed through a unified interface!");
    }
}
