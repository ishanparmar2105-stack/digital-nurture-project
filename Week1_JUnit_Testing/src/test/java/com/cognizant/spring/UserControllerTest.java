package com.cognizant.spring;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

// Spring Test Exercise 3 & Mock Dependencies Exercise 1: Mocking Service Dependency in a Controller Test
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetUser_Success() throws Exception {
        // Arrange
        User user = new User(1L, "Ishan");
        when(userService.getUserById(1L)).thenReturn(user);

        // Act & Assert
        mockMvc.perform(get("/users/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(1))
            .andExpect(jsonPath("$.name").value("Ishan"));

        verify(userService).getUserById(1L);
    }

    // Spring Test Exercise 8: Test Controller Exception Handling (GlobalExceptionHandler)
    @Test
    public void testGetUser_NotFound_Returns404() throws Exception {
        // Arrange
        when(userService.getUserById(99L)).thenThrow(new NoSuchElementException("User not found"));

        // Act & Assert
        mockMvc.perform(get("/users/99"))
            .andExpect(status().isNotFound())
            .andExpect(content().string("User not found"));

        verify(userService).getUserById(99L);
    }

    // Spring Test Exercise 5: Test Controller POST Endpoint
    @Test
    public void testCreateUser_Success() throws Exception {
        // Arrange
        User user = new User(2L, "Jane");
        when(userService.saveUser(any(User.class))).thenReturn(user);

        // Act & Assert
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value(2))
            .andExpect(jsonPath("$.name").value("Jane"));

        verify(userService).saveUser(any(User.class));
    }
}
