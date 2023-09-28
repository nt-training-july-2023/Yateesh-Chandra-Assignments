package com.capstoneproject.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capstoneproject.dto.UserResponsesDTO;
import com.capstoneproject.service.UserResponsesService;

class UserResponsesControllerTest {

    @InjectMocks
    private UserResponsesController userResponsesController;

    @Mock
    private UserResponsesService userResponsesService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUserResponses() {
        // Arrange
        UserResponsesDTO userResponsesDTO = new UserResponsesDTO();
        when(userResponsesService.addUserResponses(userResponsesDTO)).thenReturn(null);

        ResponseEntity<String> response = userResponsesController.addUserResponses(userResponsesDTO);

        // Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        String responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals("Successfully submitted", responseBody);
        verify(userResponsesService, times(1)).addUserResponses(userResponsesDTO);
    }

}
