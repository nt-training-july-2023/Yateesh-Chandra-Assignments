package com.capstoneproject.controller;

import static org.junit.jupiter.api.Assertions.*;
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
        UserResponsesDTO userResponsesDto = new UserResponsesDTO();
        when(userResponsesService.addUserResponses(userResponsesDto)).thenReturn(userResponsesDto);
        ResponseEntity<Object> responseEntity = userResponsesController.addUserResponses(userResponsesDto);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponsesDto, responseEntity.getBody());
    }

    @Test
    void testCheckStatus() {
        when(userResponsesService.findUserResponsesByUserAndQuiz(1L, 3L)).thenReturn(true);
        ResponseEntity<Object> responseEntity = userResponsesController.checkStatus(1L, 2L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertFalse((Boolean) responseEntity.getBody());
    }
}
