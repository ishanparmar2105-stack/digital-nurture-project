package com.cognizant.advanced;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ExceptionThrowerTest {
    private final ExceptionThrower thrower = new ExceptionThrower();

    @Test
    public void testThrowExceptionWithNullMessage() {
        assertThrows(IllegalArgumentException.class, () -> {
            thrower.throwException(null);
        }, "Should throw IllegalArgumentException when message is null");
    }

    @Test
    public void testThrowExceptionWithEmptyMessage() {
        assertThrows(IllegalArgumentException.class, () -> {
            thrower.throwException("   ");
        }, "Should throw IllegalArgumentException when message is empty");
    }

    @Test
    public void testThrowExceptionValidMessage() {
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            thrower.throwException("Hello");
        });
        
        assertTrue(exception.getMessage().contains("Custom exception thrown"), "Exception message should match");
    }
}
