package com.cognizant.mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class MyServiceTest {

    private ExternalApi mockApi;
    private MyService service;

    @BeforeEach
    public void setUp() {
        // Create mock object
        mockApi = mock(ExternalApi.class);
        service = new MyService(mockApi);
    }

    // Exercise 1: Mocking and Stubbing
    @Test
    public void testMockingAndStubbing() {
        // Stub the method to return a predefined value
        when(mockApi.getData()).thenReturn("Mock Data");

        // Act
        String result = service.fetchData();

        // Assert
        assertEquals("Mock Data", result);
    }

    // Exercise 2: Verifying Interactions
    @Test
    public void testVerifyInteraction() {
        // Act
        service.fetchData();

        // Verify that getData() was called on mockApi
        verify(mockApi).getData();
    }

    // Exercise 3: Argument Matching
    @Test
    public void testArgumentMatching() {
        // Stub with argument matcher
        when(mockApi.getDataWithArg(anyString())).thenReturn("Matched Result");

        // Act & Assert
        assertEquals("Matched Result", service.fetchDataWithArg("hello"));
        assertEquals("Matched Result", service.fetchDataWithArg("world"));
        
        // Verify interaction with specific or any arguments
        verify(mockApi, times(2)).getDataWithArg(anyString());
    }

    // Exercise 4: Handling Void Methods
    @Test
    public void testHandlingVoidMethods() {
        // Stub void method to do nothing (default behavior, but good to show explicitly)
        doNothing().when(mockApi).doSomething();

        // Act
        service.performAction();

        // Verify it was called
        verify(mockApi).doSomething();
    }

    // Exercise 5: Mocking and Stubbing with Multiple Returns
    @Test
    public void testMultipleReturns() {
        // Stub to return different values on consecutive calls
        when(mockApi.getData())
            .thenReturn("First Call")
            .thenReturn("Second Call")
            .thenReturn("Third Call");

        // Act & Assert
        assertEquals("First Call", service.fetchData());
        assertEquals("Second Call", service.fetchData());
        assertEquals("Third Call", service.fetchData());
        assertEquals("Third Call", service.fetchData()); // Keeps returning last value
    }

    // Exercise 6: Verifying Interaction Order
    @Test
    public void testVerifyInteractionOrder() {
        // Act
        service.performAction();
        service.fetchData();

        // Verify order: first doSomething(), then getData()
        InOrder inOrder = inOrder(mockApi);
        inOrder.verify(mockApi).doSomething();
        inOrder.verify(mockApi).getData();
    }

    // Exercise 7: Handling Void Methods with Exceptions
    @Test
    public void testHandlingVoidMethodsWithExceptions() {
        // Stub void method to throw exception
        doThrow(new RuntimeException("API Failure")).when(mockApi).doSomethingWithArg("bad-arg");

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.performActionWithArg("bad-arg");
        });
        
        assertEquals("API Failure", exception.getMessage());
        verify(mockApi).doSomethingWithArg("bad-arg");
    }
}
