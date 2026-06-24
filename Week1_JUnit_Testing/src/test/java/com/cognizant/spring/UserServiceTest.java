package com.cognizant.spring;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    // Spring Test Exercise 2 & Mock Dependencies Exercise 2: Mocking a Repository in a Service Test
    @Test
    public void testGetUserById_Success() {
        // Arrange
        User user = new User(1L, "Ishan Parmar");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // Act
        User result = userService.getUserById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("Ishan Parmar", result.getName());
        verify(userRepository).findById(1L);
    }

    // Spring Test Exercise 6: Test Service Exception Handling
    @Test
    public void testGetUserById_NotFound_ThrowsException() {
        // Arrange
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        NoSuchElementException exception = assertThrows(NoSuchElementException.class, () -> {
            userService.getUserById(99L);
        });

        assertEquals("User not found with id: 99", exception.getMessage());
        verify(userRepository).findById(99L);
    }

    @Test
    public void testSaveUser_Success() {
        // Arrange
        User user = new User(2L, "Jane Doe");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        User result = userService.saveUser(user);

        // Assert
        assertNotNull(result);
        assertEquals("Jane Doe", result.getName());
        verify(userRepository).save(user);
    }

    @Test
    public void testSaveUser_EmptyName_ThrowsException() {
        // Arrange
        User user = new User(2L, "  ");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            userService.saveUser(user);
        });

        assertEquals("User name cannot be empty", exception.getMessage());
        verifyNoInteractions(userRepository);
    }
}
