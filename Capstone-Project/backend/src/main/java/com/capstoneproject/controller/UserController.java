package com.capstoneproject.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstoneproject.dto.LoginDTO;
import com.capstoneproject.dto.UserDTO;
import com.capstoneproject.exceptions.AuthenticationException;
import com.capstoneproject.exceptions.CustomException;
import com.capstoneproject.exceptions.DuplicateKeyException;
import com.capstoneproject.exceptions.ValidationException;
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
        try {
            String id = userService.addUser(userDTO);
            return id;
        } catch (DuplicateKeyException e) {
            throw new DuplicateKeyException("Email already exists");
        } catch (ValidationException e) {
            throw new ValidationException("Invalid data");
        } catch (CustomException e) {
            return "An Error occured..!";
        }
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
        try {
            LoginResponse loginResponse = userService.loginUser(loginDTO);
            return ResponseEntity.ok(loginResponse);
        } catch (AuthenticationException | ValidationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("An error occured while processing the request.");
        }
    }
}
