package com.capstoneproject.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstoneproject.dto.AllResultsDTO;
import com.capstoneproject.service.AllResultsService;

@RestController
@RequestMapping("/api/v1/allresult")
@CrossOrigin(origins = "*")
public class AllResultsController {

    @Autowired
    private AllResultsService allResultsService;

    @GetMapping
    public final ResponseEntity<Object> getAllResults(){
        List<AllResultsDTO> allResultsDTO = allResultsService.getAllResults();
        return ResponseEntity.ok(allResultsDTO);
    }

    @GetMapping("/{userId}/{quizName}")
    public final ResponseEntity<Object> getResultsByUserIdAndQuizName(@PathVariable Long userId, @PathVariable String quizName){
        Optional<AllResultsDTO> allResults = allResultsService.getResultsByUserIdAndQuizName(userId, quizName);
        return ResponseEntity.ok(allResults);
    }

    @GetMapping("/{emailId}")
    public final ResponseEntity<Object> getResultsByEmail(@PathVariable String email){
        List<AllResultsDTO> allResultsDto = allResultsService.getResultsByEmail(email);
        return ResponseEntity.ok(allResultsDto);
    }
}
