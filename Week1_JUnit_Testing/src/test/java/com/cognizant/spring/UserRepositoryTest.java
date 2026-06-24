package com.cognizant.spring;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

// Spring Test Exercise 7: Test Custom Repository Query
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByName() {
        // Arrange
        User user1 = new User(1L, "Alice");
        User user2 = new User(2L, "Bob");
        User user3 = new User(3L, "Alice");
        
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        // Act
        List<User> results = userRepository.findByName("Alice");

        // Assert
        assertEquals(2, results.size());
        assertTrue(results.stream().anyMatch(u -> u.getId().equals(1L)));
        assertTrue(results.stream().anyMatch(u -> u.getId().equals(3L)));
    }
}
