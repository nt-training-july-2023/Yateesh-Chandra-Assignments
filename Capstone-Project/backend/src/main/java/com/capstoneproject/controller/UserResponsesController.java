package com.capstoneproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstoneproject.dto.UserResponsesDTO;
import com.capstoneproject.service.UserResponsesService;

import jakarta.validation.Valid;

/**
 * This is the controller for User Responses.
 */
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("response")
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
    public final ResponseEntity<String> addUserResponses(
            @RequestBody @Valid final UserResponsesDTO response) {
        responseService.addUserResponses(response);
        return ResponseEntity.ok("Successfully submitted");
    }
}
