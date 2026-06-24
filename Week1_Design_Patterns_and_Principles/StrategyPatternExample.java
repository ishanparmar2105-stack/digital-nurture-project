/*
 * Exercise 8: Implementing the Strategy Pattern
 * 
 * Scenario: Payment system where different payment methods can be selected at runtime.
 */

interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    public CreditCardPayment(String cardNumber) { this.cardNumber = cardNumber; }
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card ending in " + cardNumber.substring(cardNumber.length() - 4));
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;
    public PayPalPayment(String email) { this.email = email; }
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal account: " + email);
    }
}

class BitcoinPayment implements PaymentStrategy {
    private String walletAddress;
    public BitcoinPayment(String walletAddress) { this.walletAddress = walletAddress; }
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Bitcoin wallet: " + walletAddress);
    }
}

class PaymentContext {
    private PaymentStrategy paymentStrategy;
    public void setPaymentStrategy(PaymentStrategy strategy) { this.paymentStrategy = strategy; }
    public void executePayment(double amount) {
        if (paymentStrategy == null) {
            System.out.println("No payment strategy selected!");
            return;
        }
        paymentStrategy.pay(amount);
    }
}

public class StrategyPatternExample {
    public static void main(String[] args) {
        System.out.println("=== Strategy Pattern Example ===\n");
        PaymentContext context = new PaymentContext();

        context.setPaymentStrategy(new CreditCardPayment("1234567890121234"));
        context.executePayment(150.00);

        context.setPaymentStrategy(new PayPalPayment("user@example.com"));
        context.executePayment(89.99);

        context.setPaymentStrategy(new BitcoinPayment("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa"));
        context.executePayment(500.00);
    }
}
