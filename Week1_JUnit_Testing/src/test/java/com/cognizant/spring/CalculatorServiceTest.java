package com.cognizant.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    // Spring Test Exercise 1: Basic Unit Test for a Service Method
    @Test
    public void testAdd() {
        int result = calculatorService.add(5, 10);
        assertEquals(15, result, "5 + 10 should be 15");
    }

    // Spring Test Exercise 9: Parameterized Test with JUnit
    @ParameterizedTest
    @CsvSource({
        "1, 1, 2",
        "2, 3, 5",
        "10, -5, 5",
        "0, 0, 0"
    })
    public void testAddParameterized(int a, int b, int expected) {
        assertEquals(expected, calculatorService.add(a, b), 
            a + " + " + b + " should be " + expected);
    }
}
