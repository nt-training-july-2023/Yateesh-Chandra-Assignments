package com.capstoneproject.service;

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

import com.capstoneproject.dto.AllResultsDTO;
import com.capstoneproject.models.AllResults;
import com.capstoneproject.repository.AllResultsRepository;

class AllResultsServiceTest {

    @InjectMocks
    private AllResultsService allResultsService;

    @Mock
    private AllResultsRepository allResultsRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetAllResults() {
        List<AllResults> allResults = new ArrayList<>();
        AllResults allResults1 = new AllResults();
        allResults1.setResultId(1L);
        AllResults allResults2 = new AllResults();
        allResults2.setResultId(2L);
        allResults.add(allResults1);
        allResults.add(allResults2);
        when(allResultsRepo.findAll()).thenReturn(allResults);
        List<AllResultsDTO> allResultsDto = allResultsService.getAllResults();
        assertEquals(2, allResultsDto.size());
    }

    @Test
    public void testGetResultsByUserAndQuizName() {
        AllResults allResults = new AllResults();
        allResults.setResultId(1L);
        allResults.setUserId(2L);
        allResults.setUserName("Test User");
        allResults.setCategoryName("Test Category");
        allResults.setQuizName("Test Quiz");
        when(allResultsRepo.getResultsByUserIdAndQuizName(2L,"Test Quiz")).thenReturn(Optional.of(allResults));
        Optional<AllResultsDTO> allResultsDto = allResultsService.getResultsByUserIdAndQuizName(2L, "Test Quiz");
        assertTrue(allResultsDto.isPresent());
        
        AllResultsDTO resultsDto = allResultsDto.get();
        assertEquals(1L, resultsDto.getResultId());
        assertEquals(2L, resultsDto.getUserId());
        assertEquals("Test User", resultsDto.getUserName());
        assertEquals("Test Category", resultsDto.getCategoryName());
        assertEquals("Test Quiz", resultsDto.getQuizName());
    }

    @Test
    public void testGetResultsByEmail() {
        List<AllResults> allResults = new ArrayList<>();
        AllResults allResults1 = new AllResults();
        allResults1.setResultId(1L);
        AllResults allResults2 = new AllResults();
        allResults2.setResultId(2L);
        allResults.add(allResults1);
        allResults.add(allResults2);
        when(allResultsRepo.getResultsByEmail("test@gmail.com")).thenReturn(allResults);
        List<AllResultsDTO> resultsDto = allResultsService.getResultsByEmail("test@gmail.com");
        assertEquals(2, resultsDto.size());
    }
}
