/*
 * Exercise 7: Implementing the Observer Pattern
 * 
 * Scenario: Stock market monitoring where multiple clients get notified on price changes.
 */

import java.util.ArrayList;
import java.util.List;

interface Stock {
    void registerObserver(Observer observer);
    void deregisterObserver(Observer observer);
    void notifyObservers();
}

interface Observer {
    void update(String stockName, double stockPrice);
}

class StockMarket implements Stock {
    private List<Observer> observers;
    private String stockName;
    private double stockPrice;

    public StockMarket(String stockName, double initialPrice) {
        this.observers = new ArrayList<>();
        this.stockName = stockName;
        this.stockPrice = initialPrice;
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
        System.out.println("Observer registered for " + stockName);
    }

    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
        System.out.println("Observer deregistered from " + stockName);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockName, stockPrice);
        }
    }

    public void setStockPrice(double newPrice) {
        System.out.println("\n" + stockName + " price changed: $" + stockPrice + " -> $" + newPrice);
        this.stockPrice = newPrice;
        notifyObservers();
    }
}

class MobileApp implements Observer {
    private String appName;
    public MobileApp(String appName) { this.appName = appName; }

    public void update(String stockName, double stockPrice) {
        System.out.println("[" + appName + " - Mobile] " + stockName + " is now $" + stockPrice);
    }
}

class WebApp implements Observer {
    private String appName;
    public WebApp(String appName) { this.appName = appName; }

    public void update(String stockName, double stockPrice) {
        System.out.println("[" + appName + " - Web] " + stockName + " is now $" + stockPrice);
    }
}

public class ObserverPatternExample {
    public static void main(String[] args) {
        System.out.println("=== Observer Pattern Example ===\n");
        StockMarket googleStock = new StockMarket("GOOGL", 2800.00);
        Observer mobileApp = new MobileApp("StockTracker");
        Observer webApp = new WebApp("StockDashboard");
        Observer mobileApp2 = new MobileApp("InvestorApp");
        googleStock.registerObserver(mobileApp);
        googleStock.registerObserver(webApp);
        googleStock.registerObserver(mobileApp2);
        googleStock.setStockPrice(2850.50);
        googleStock.setStockPrice(2900.75);
        googleStock.deregisterObserver(mobileApp2);
        googleStock.setStockPrice(2875.00);
    }
}
