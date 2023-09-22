package com.capstoneproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstoneproject.dto.AllResultsDTO;
import com.capstoneproject.service.AllResultsService;

/**
 * This is the controller class for the All Results.
 */
@RestController
@RequestMapping("/api/v1/allresult")
@CrossOrigin
public class AllResultsController {

    /**
     * This autowires All Results Service.
     */
    @Autowired
    private AllResultsService allResultsService;

    /**
     * This is the Response Entity of All Results.
     * @return OK status.
     */
    @GetMapping
    public final ResponseEntity<Object> getAllResults() {
        List<AllResultsDTO> allResultsDTO = allResultsService.getAllResults();
        return ResponseEntity.ok(allResultsDTO);
    }

    /**
     * This is the Response Entity for the Get Results by User and Quiz method.
     * @param userId - Long type.
     * @param quizName - String type.
     * @return the OK status.
     */
    @GetMapping("/{userId}/{quizName}")
    public final ResponseEntity<Object> getResultsByUserIdAndQuizName(
            @PathVariable final Long userId,
            @PathVariable final String quizName) {
        Optional<AllResultsDTO> allResults = allResultsService
                .getResultsByUserIdAndQuizName(userId, quizName);
        return ResponseEntity.ok(allResults);
    }

    /**
     * This is the Response Entity.
     * @param userId - String.
     * @return the Ok status.
     */
    @GetMapping("/{userId}")
    public final ResponseEntity<Object> getResultsByUserId(
            @PathVariable final Long userId) {
        List<AllResultsDTO> allResultsDto = allResultsService
                .getResultsByUserId(userId);
        return ResponseEntity.ok(allResultsDto);
    }
}
