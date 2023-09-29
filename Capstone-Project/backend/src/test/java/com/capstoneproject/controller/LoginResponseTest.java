package com.capstoneproject.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LoginResponseTest {

    @Test
    void testLoginResponseNoArgsConstructor() {
        LoginResponse loginResponse = new LoginResponse();
        assertEquals(null, loginResponse.getMessage());
        assertEquals(null, loginResponse.getStatus());
        assertEquals(null, loginResponse.getUserRole());
        assertEquals(null, loginResponse.getUserId());
        
    }
    
    @Test
    void testLoginResponseAllArgsConstructor() {
        String message = "Hello";
        boolean status = true;
        String userRole = "ADMIN";
        String name = "Yateesh";
        String email = "test@nucelusteq.com";
        Long userId = 7L;
        LoginResponse loginResponse = new LoginResponse(message, status, userRole, userId, name, email);
        assertEquals(message, loginResponse.getMessage());
        assertEquals(status, loginResponse.getStatus());
        assertEquals(userRole, loginResponse.getUserRole());
        assertEquals(userId, loginResponse.getUserId());
        assertEquals(name, loginResponse.getName());
        assertEquals(email, loginResponse.getEmail());
    }
    
    @Test
    void testLoginResponseGetterAndSetter() {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setMessage("Hello");
        loginResponse.setStatus(true);
        loginResponse.setUserRole("USER");
        
        assertEquals("Hello",loginResponse.getMessage());
        assertEquals(true,loginResponse.getStatus());
        assertEquals("USER",loginResponse.getUserRole());
    }

    @Test
    void testLoginResponseAlternateConstructor() {
        String message = "Hello";
        boolean status = true;
        LoginResponse loginResponse = new LoginResponse(message, status);
        assertEquals(message, loginResponse.getMessage());
        assertEquals(status, loginResponse.getStatus());
        assertEquals(null, loginResponse.getUserRole());
    }
}
