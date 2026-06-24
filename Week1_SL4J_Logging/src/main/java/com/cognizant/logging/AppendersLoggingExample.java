package com.cognizant.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppendersLoggingExample {
    private static final Logger logger = LoggerFactory.getLogger(AppendersLoggingExample.class);

    public static void main(String[] args) {
        System.out.println("--- Running Exercise 3: Logging to Console and File (app.log) ---");
        
        logger.debug("Debug log: starting process...");
        logger.info("Info log: system initialized.");
        logger.warn("Warn log: configuration is using defaults.");
        logger.error("Error log: database connection timed out, retrying...");
        
        System.out.println("Exercise 3 Logging completed. Check 'app.log' in the project directory.");
    }
}
