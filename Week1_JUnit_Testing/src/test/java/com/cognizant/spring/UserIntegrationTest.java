package com.cognizant.spring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

// Spring Test Exercise 4 & Mock Dependencies Exercise 3: Integration Test with Spring Boot
@SpringBootTest
@AutoConfigureMockMvc
@Transactional // Rollback database changes after each test to keep tests isolated
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        userRepository.deleteAll();
    }

    @Test
    public void testFullFlow_CreateAndGetCustomer() throws Exception {
        User newUser = new User(100L, "Integration Test User");

        // 1. Post to create the user
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(100))
            .andExpect(jsonPath("$.name").value("Integration Test User"));

        // Verify it was actually saved in database
        assertTrue(userRepository.findById(100L).isPresent());
        assertEquals("Integration Test User", userRepository.findById(100L).get().getName());

        // 2. Get the created user
        mockMvc.perform(get("/users/100"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(100))
            .andExpect(jsonPath("$.name").value("Integration Test User"));
    }
}
