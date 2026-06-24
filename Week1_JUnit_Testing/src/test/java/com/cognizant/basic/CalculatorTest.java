package com.cognizant.basic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    private Calculator calculator;

    // Test Fixture Setup
    @BeforeEach
    public void setUp() {
        System.out.println("Setting up Calculator instance before test...");
        calculator = new Calculator();
    }

    // Test Fixture Teardown
    @AfterEach
    public void tearDown() {
        System.out.println("Cleaning up Calculator instance after test...");
        calculator = null;
    }

    @Test
    public void testAdd() {
        // Arrange
        int a = 10;
        int b = 20;

        // Act
        int result = calculator.add(a, b);

        // Assert
        assertEquals(30, result, "10 + 20 should equal 30");
    }

    @Test
    public void testSubtract() {
        // Arrange
        int a = 50;
        int b = 20;

        // Act
        int result = calculator.subtract(a, b);

        // Assert
        assertEquals(30, result, "50 - 20 should equal 30");
    }

    @Test
    public void testMultiply() {
        // Arrange
        int a = 5;
        int b = 6;

        // Act
        int result = calculator.multiply(a, b);

        // Assert
        assertEquals(30, result, "5 * 6 should equal 30");
    }

    @Test
    public void testDivide() {
        // Arrange
        int a = 60;
        int b = 2;

        // Act
        double result = calculator.divide(a, b);

        // Assert
        assertEquals(30.0, result, "60 / 2 should equal 30.0");
    }

    @Test
    public void testDivideByZero() {
        // Assert exception
        assertThrows(ArithmeticException.class, () -> {
            calculator.divide(10, 0);
        }, "Dividing by zero should throw ArithmeticException");
    }
}
