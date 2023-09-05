package com.capstoneproject.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capstoneproject.dto.LoginDTO;
import com.capstoneproject.dto.UserDTO;
import com.capstoneproject.exceptions.AuthenticationException;
import com.capstoneproject.exceptions.CustomException;
import com.capstoneproject.exceptions.EntityNotFoundException;
import com.capstoneproject.exceptions.ValidationException;
import com.capstoneproject.models.UserAssessment;
import com.capstoneproject.service.UserAssessmentService;
import com.capstoneproject.service.UserService;

class UserControllerTest {

    @Mock
    private UserAssessmentService userAssessmentService;
    
    @Mock
    private UserService userService;
    
    @InjectMocks
    private UserController userController;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testSaveUser() {
        UserDTO userDto = new UserDTO();
        userDto.setName("testUser");
        when(userService.addUser(userDto)).thenReturn("123");
        
        String response = userController.saveUser(userDto);
        verify(userService, times(1)).addUser(userDto);
        assert response.equals("123");
    }

    @Test
    public void testLoginUser() {
        LoginDTO loginDto = new LoginDTO();
        loginDto.setEmail("test@gmail.com");
        when(userService.loginUser(loginDto)).thenReturn(new LoginResponse());
        
        ResponseEntity<?> response = userController.loginUser(loginDto);
        verify(userService, times(1)).loginUser(loginDto);
        assert response.getStatusCode()==HttpStatus.OK;
    }
    
    @Test
    void testSaveUserCustomException() {
        UserDTO mockUserDTO = new UserDTO();
        when(userService.addUser(mockUserDTO)).thenThrow(new CustomException("Custom Error"));

        String response = userController.saveUser(mockUserDTO);
        assertEquals("An Error occured..!", response);
    }
    
    @Test
    public void testGetUserAssessmentsByUserEntityNotFoundException() {
        Long userId = 123L;
        when(userAssessmentService.getUserAssessmentsByUser(userId)).thenThrow(new EntityNotFoundException("Entity not found"));
        ResponseEntity<List<UserAssessment>> response = userController.getUserAssessmentsByUser(userId);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    
    @Test
    public void testGetUserAssessmentsByUser() {
        Long userId = 123L;
        when(userAssessmentService.getUserAssessmentsByUser(userId)).thenReturn(List.of(new UserAssessment()));
        ResponseEntity<List<UserAssessment>> response = userController.getUserAssessmentsByUser(userId);
        verify(userAssessmentService, times(1)).getUserAssessmentsByUser(userId);
        assert response.getStatusCode()== HttpStatus.OK;
    }
    
    @Test
    void testLoginUserAuthenticationException() {
        LoginDTO mockLoginDTO = new LoginDTO();
        when(userService.loginUser(mockLoginDTO)).thenThrow(new AuthenticationException("Authentication Error"));

        ResponseEntity<?> response = userController.loginUser(mockLoginDTO);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Authentication Error", response.getBody());
    }
    
    @Test
    void testLoginUserValidationException() {
        LoginDTO mockLoginDTO = new LoginDTO();
        when(userService.loginUser(mockLoginDTO)).thenThrow(new ValidationException("Validation Error"));

        ResponseEntity<?> response = userController.loginUser(mockLoginDTO);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Validation Error", response.getBody());
    }
    
    @Test
    void testLoginUserCustomException() {
        LoginDTO mockLoginDTO = new LoginDTO();
        when(userService.loginUser(mockLoginDTO)).thenThrow(new CustomException("Custom Error"));

        ResponseEntity<?> response = userController.loginUser(mockLoginDTO);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("An error occured while processing the request.", response.getBody());
    }
}
