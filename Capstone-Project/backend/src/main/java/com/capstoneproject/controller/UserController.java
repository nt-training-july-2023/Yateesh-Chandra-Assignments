package com.capstoneproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstoneproject.dto.LoginDTO;
import com.capstoneproject.dto.UserDTO;
import com.capstoneproject.service.UserService;

/**
 * This is the Controller class for User.
 */
@RestController
@CrossOrigin
@RequestMapping("api/v1/user")
public class UserController {
    /**
     * This variable is used to perform operations on UserService.
     */
    @Autowired
    private UserService userService;

    /**
     * This method is used for new users to register.
     * @param userDTO of UserDTO class.
     * @return the id after registering.
     */
    @PostMapping(path = "/save")
    public final String saveUser(@RequestBody final UserDTO userDTO) {
            String id = userService.addUser(userDTO);
            return id;
    }

    /**
     * This is for Existing users to login.
     *
     * @param loginDTO of LoginDTO.
     * @return the Id if it exists.
     */
    @PostMapping(path = "/login")
    public final ResponseEntity<?> loginUser(
            @RequestBody final LoginDTO loginDTO) {
            LoginResponse loginResponse = userService.loginUser(loginDTO);
            return ResponseEntity.ok(loginResponse);
    }
}
