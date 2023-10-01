package com.capstoneproject.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import com.capstoneproject.response.Response;
import com.capstoneproject.response.SuccessMessages;
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

        UserDTO userDTO = new UserDTO();
        when(userService.addUser(userDTO)).thenReturn(null);

        ResponseEntity<Response> response = userController.saveUser(userDTO);
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        Response responseBody = response.getBody();
        assertNotNull(responseBody);
        assertEquals(HttpStatus.CREATED.value(), responseBody.getCode());
        assertEquals(SuccessMessages.REGISTRATION_SUCCESS, responseBody.getMessage());
        verify(userService, times(1)).addUser(userDTO);
    }

    @Test
    public void testLoginUser() {
        LoginDTO loginDto = new LoginDTO();
        loginDto.setEmail("test@nucleusteq.com");
        when(userService.loginUser(loginDto)).thenReturn(new LoginResponse());
        ResponseEntity<LoginResponse> response = userController.loginUser(loginDto);
        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(userService, times(1)).loginUser(loginDto);
    }

    @Test
    public void deleteUser() {
        Long userId = 1L;
        ResponseEntity<Response> response = userController.deleteUser(userId);
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        Response responseBody = response.getBody();
        assertEquals(HttpStatus.NO_CONTENT.value(), responseBody.getCode());
        assertEquals(SuccessMessages.USER_DELETE_SUCCESS, responseBody.getMessage());
        verify(userService, times(1)).deleteUser(userId);
    }
}
