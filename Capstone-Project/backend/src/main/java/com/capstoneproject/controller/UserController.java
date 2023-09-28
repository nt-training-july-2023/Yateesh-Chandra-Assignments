package com.capstoneproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstoneproject.dto.LoginDTO;
import com.capstoneproject.dto.UserDTO;
import com.capstoneproject.response.LoginResponse;
import com.capstoneproject.service.UserService;

import jakarta.validation.Valid;

/**
 * This is the Controller class for User.
 */
@RestController
@CrossOrigin
@RequestMapping("user")
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
    public final ResponseEntity<String> saveUser(
            @RequestBody @Valid final UserDTO userDTO) {
            userService.addUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    "User Registered successfully");
    }

    /**
     * This is for Existing users to login.
     *
     * @param loginDTO of LoginDTO.
     * @return the Id if it exists.
     */
    @PostMapping(path = "/login")
    public final ResponseEntity<LoginResponse> loginUser(
            @RequestBody @Valid final LoginDTO loginDTO) {
            LoginResponse loginResponse = userService.loginUser(loginDTO);
            return ResponseEntity.ok(loginResponse);
    }

    @DeleteMapping(path = "/{userId}")
    public final ResponseEntity<Void> deleteUser(
            @PathVariable Long userId){
        userService.deleteUser(userId);
        String message = "Deleted Successfully";
        return ResponseEntity.noContent().header("Message", message).build();
    }
}
