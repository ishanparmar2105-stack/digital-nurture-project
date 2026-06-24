package com.cognizant.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterizedLoggingExample {
    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggingExample.class);

    public static void main(String[] args) {
        System.out.println("--- Running Exercise 2: Parameterized Logging ---");
        
        String username = "Ishan Parmar";
        int loginAttempts = 3;
        String ipAddress = "192.168.1.105";

        // Single parameter logging
        logger.info("User '{}' attempted to log in.", username);

        // Multiple parameters logging
        logger.warn("Failed login attempt #{} for user '{}' from IP address {}.", loginAttempts, username, ipAddress);

        // Logging exception with parameterized message
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            logger.error("An error occurred during math operations for user '{}': {}", username, e.getMessage(), e);
        }
        
        System.out.println("Exercise 2 Logging completed successfully.");
    }
}
