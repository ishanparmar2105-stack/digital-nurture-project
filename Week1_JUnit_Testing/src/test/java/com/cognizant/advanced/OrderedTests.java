package com.cognizant.advanced;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedTests {

    private static String steps = "";

    @BeforeAll
    public static void setUpAll() {
        steps = "";
    }

    @Test
    @Order(1)
    public void testFirstStep() {
        steps += "First";
        assertEquals("First", steps);
    }

    @Test
    @Order(2)
    public void testSecondStep() {
        steps += "Second";
        assertEquals("FirstSecond", steps);
    }

    @Test
    @Order(3)
    public void testThirdStep() {
        steps += "Third";
        assertEquals("FirstSecondThird", steps);
    }
}
