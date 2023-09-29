package com.capstoneproject.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        ResponseEntity<Object> responseEntity = allResultsController.getAllResults();
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(allResultsDto, responseEntity.getBody());
    }

    @Test
    void testGetResultsByEmail() {
        List<AllResultsDTO> allResultsDto = new ArrayList<>();
        AllResultsDTO result1 = new AllResultsDTO();
        AllResultsDTO result2 = new AllResultsDTO();
        allResultsDto.add(result1);
        allResultsDto.add(result2);
        when(allResultsService.getResultsByUserId(5L)).thenReturn(allResultsDto);
        ResponseEntity<Object> responseEntity = allResultsController.getResultsByUserId(5L);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(allResultsDto, responseEntity.getBody());
    }

    @Test
    void testGetResultsByUserIdAndQuizName() {
        AllResultsDTO allResultsDto = new AllResultsDTO();
        when(allResultsService.getResultsByUserIdAndQuizName(1L, "Test Quiz")).thenReturn(Optional.of(allResultsDto));
        ResponseEntity<Object> responseEntity = allResultsController.getResultsByUserIdAndQuizName(1L,"Test Quiz");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
