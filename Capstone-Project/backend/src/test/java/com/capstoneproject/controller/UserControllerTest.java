package com.capstoneproject.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.capstoneproject.dto.LoginDTO;
import com.capstoneproject.dto.UserDTO;
import com.capstoneproject.response.LoginResponse;
import com.capstoneproject.service.UserService;

class UserControllerTest {

    
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

}
