package com.capstoneproject.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("results")
@CrossOrigin
public class AllResultsController {

    /**
     * This autowires All Results Service.
     */
    @Autowired
    private AllResultsService allResultsService;

    /**
     * Creating instance for the Logger
     */
    private Logger logger = LoggerFactory.getLogger(AllResultsController.class);

    /**
     * This is the Response Entity of All Results.
     * @return OK status.
     */
    @GetMapping
    public final ResponseEntity<List<AllResultsDTO>> getAllResults() {
        List<AllResultsDTO> allResultsDTO = allResultsService.getAllResults();
        logger.info("Successfully fetched Results of all Users");
        return ResponseEntity.ok(allResultsDTO);
    }

    /**
     * This is the Response Entity.
     * @param userId - String.
     * @return the Ok status.
     */
    @GetMapping("/{userId}")
    public final ResponseEntity<List<AllResultsDTO>> getResultsByUserId(
            @PathVariable final Long userId) {
        List<AllResultsDTO> allResultsDto = allResultsService
                .getResultsByUserId(userId);
        logger.info("Successfully fetched Results User Id : " + userId);
        return ResponseEntity.ok(allResultsDto);
    }
}
