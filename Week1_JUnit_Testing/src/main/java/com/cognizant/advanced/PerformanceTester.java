package com.cognizant.advanced;

public class PerformanceTester {
    public void performTask() {
        try {
            // Simulate a time-consuming task
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
    public void performSlowTask() {
        try {
            // Simulate a very slow task
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
