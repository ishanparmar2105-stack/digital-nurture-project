package com.cognizant.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingExample {
    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {
        System.out.println("--- Running Exercise 1: SLF4J Error and Warning Levels ---");
        logger.trace("This is a trace message (might not show depending on level)");
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warning message");
        logger.error("This is an error message");
        System.out.println("Exercise 1 Logging completed successfully.");
    }
}
