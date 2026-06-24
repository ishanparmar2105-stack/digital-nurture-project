package com.cognizant.advanced;

import static org.junit.jupiter.api.Assertions.assertTimeout;
import java.time.Duration;
import org.junit.jupiter.api.Test;

public class PerformanceTesterTest {
    private final PerformanceTester tester = new PerformanceTester();

    @Test
    public void testPerformTaskTimeout() {
        // Assert that task completes within 500 milliseconds
        assertTimeout(Duration.ofMillis(500), () -> {
            tester.performTask();
        }, "Task should complete within 500ms");
    }

    @Test
    public void testPerformSlowTaskTimeout() {
        // This test would fail if we expect it to complete within 500ms
        // Let's assert it completes within 2 seconds
        assertTimeout(Duration.ofSeconds(2), () -> {
            tester.performSlowTask();
        }, "Slow task should complete within 2s");
    }
}
