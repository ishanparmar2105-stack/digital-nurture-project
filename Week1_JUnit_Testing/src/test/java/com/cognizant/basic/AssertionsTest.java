package com.cognizant.basic;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class AssertionsTest {
    @Test
    public void testAssertions() {
        // Assert equals
        assertEquals(5, 2 + 3, "2 + 3 should equal 5");

        // Assert true
        assertTrue(5 > 3, "5 should be greater than 3");

        // Assert false
        assertFalse(5 < 3, "5 should not be less than 3");

        // Assert null
        assertNull(null, "Object should be null");

        // Assert not null
        assertNotNull(new Object(), "Object should not be null");
    }
}
