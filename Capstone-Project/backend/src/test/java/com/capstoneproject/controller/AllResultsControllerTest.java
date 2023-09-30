package com.capstoneproject.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capstoneproject.dto.AllResultsDTO;
import com.capstoneproject.service.AllResultsService;

class AllResultsControllerTest {

    @InjectMocks
    private AllResultsController allResultsController;

    @Mock
    private AllResultsService allResultsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllResults() {
        List<AllResultsDTO> allResultsDto = new ArrayList<>();
        AllResultsDTO result1 = new AllResultsDTO();
        AllResultsDTO result2 = new AllResultsDTO();
        allResultsDto.add(result1);
        allResultsDto.add(result2);
        when(allResultsService.getAllResults()).thenReturn(allResultsDto);
        ResponseEntity<List<AllResultsDTO>> responseEntity = allResultsController.getAllResults();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(allResultsDto, responseEntity.getBody());
    }

    @Test
    public void testGetResultsByUserId() {
        Long userId = 1L;
        List<AllResultsDTO> resultsList = new ArrayList<>();
        resultsList.add(new AllResultsDTO());
        when(allResultsService.getResultsByUserId(userId)).thenReturn(resultsList);
        ResponseEntity<List<AllResultsDTO>> response = allResultsController.getResultsByUserId(userId);
        verify(allResultsService, times(1)).getResultsByUserId(userId);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(resultsList, response.getBody());
    }
}