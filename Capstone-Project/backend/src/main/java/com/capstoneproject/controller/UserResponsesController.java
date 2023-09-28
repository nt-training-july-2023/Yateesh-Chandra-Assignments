package com.capstoneproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
     * Creating instance for logger.
     */
    private Logger logger = LoggerFactory.getLogger(
            UserResponsesController.class);
    /**
     * This is the addUser Response Entity.
     * @param response - UserResponse DTO.
     * @return Ok status.
     */
    @PostMapping("/add")
    public final ResponseEntity<String> addUserResponses(
            @RequestBody @Valid final UserResponsesDTO response) {
        responseService.addUserResponses(response);
        logger.info("Responses added Successfully");
        return ResponseEntity.ok("Successfully submitted");
    }
}
