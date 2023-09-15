package com.capstoneproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstoneproject.dto.UserResponsesDTO;
import com.capstoneproject.service.UserResponsesService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/response")
public class UserResponsesController {

    @Autowired
    private UserResponsesService responseService;

    @PostMapping("/add")
    public final ResponseEntity<Object> addUserResponses(@RequestBody UserResponsesDTO response){
        UserResponsesDTO responseDto = responseService.addUserResponses(response);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/status/{userId}/{quizId}")
    public final ResponseEntity<Object> checkStatus(@PathVariable final Long userId, @PathVariable final Long quizId){
        boolean responses = responseService.findUserResponsesByUserAndQuiz(userId, quizId);
        return ResponseEntity.ok(responses);
    }
}
