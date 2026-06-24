package com.cognizant.advanced;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EvenCheckerTest {
    private final EvenChecker evenChecker = new EvenChecker();

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 10, 100, -20})
    public void testIsEvenWithEvenNumbers(int number) {
        assertTrue(evenChecker.isEven(number), number + " should be even");
    }
}
