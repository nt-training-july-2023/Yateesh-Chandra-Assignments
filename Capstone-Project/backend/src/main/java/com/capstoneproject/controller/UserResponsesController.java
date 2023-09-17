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

/**
 * This is the controller for User Responses.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("api/v1/response")
public class UserResponsesController {

    /**
     * This autowires Response Service.
     */
    @Autowired
    private UserResponsesService responseService;

    /**
     * This is the addUser Response Entity.
     * @param response - UserResponse DTO.
     * @return Ok status.
     */
    @PostMapping("/add")
    public final ResponseEntity<Object> addUserResponses(
            @RequestBody final UserResponsesDTO response) {
        UserResponsesDTO responseDto = responseService
                .addUserResponses(response);
        return ResponseEntity.ok(responseDto);
    }

    /**
     * This is the Check Status method.
     * @param userId - Long type.
     * @param quizId - Long type.
     * @return the Ok status.
     */
    @GetMapping("/status/{userId}/{quizId}")
    public final ResponseEntity<Object> checkStatus(
            @PathVariable final Long userId, @PathVariable final Long quizId) {
        boolean responses = responseService
                .findUserResponsesByUserAndQuiz(userId, quizId);
        return ResponseEntity.ok(responses);
    }
}
