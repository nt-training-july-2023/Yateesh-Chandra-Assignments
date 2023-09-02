package com.capstoneproject.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.capstoneproject.controller.LoginResponse;
import com.capstoneproject.dto.LoginDTO;
import com.capstoneproject.dto.UserDTO;
import com.capstoneproject.exceptions.CustomException;
import com.capstoneproject.exceptions.ValidationException;
import com.capstoneproject.models.User;
import com.capstoneproject.models.UserAssessment;
import com.capstoneproject.repository.UserAssessmentRepository;
import com.capstoneproject.repository.UserRepository;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    
    @Mock
    private UserAssessmentRepository userAssessmentRepository;
    
    @Mock
    private PasswordEncoder passwordEncoder;
    
    @InjectMocks
    private UserService userService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetUserAssessmentByUser() {
        User user = new User();
        List<UserAssessment> expectedUserAssessments = Collections.singletonList(new UserAssessment());
        when(userAssessmentRepository.findByUser(user)).thenReturn (expectedUserAssessments);
        List<UserAssessment> actualUserAssessments = userService.getUserAssessmentByUser(user);
        assertEquals(expectedUserAssessments, actualUserAssessments);
    }

    @Test
    public void testAddUser() {
        UserDTO userDto = new UserDTO();
        userDto.setId(1L);
        userDto.setName("Yateesh Chandra");
        userDto.setEmail("yateesh.chandra@nucleusteq.com");
        userDto.setPassword("03072001");
        userDto.setUserRole("USER");
        userDto.setPhoneNumber("8179074291");

        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setUserRole(userDto.getUserRole());
        user.setPhoneNumber(userDto.getPhoneNumber());
        
        when(passwordEncoder.encode(userDto.getPassword())).thenReturn("encoded Password");
        when(userRepository.save(user)).thenReturn(user);
        String actualName = userService.addUser(userDto);
        assertEquals(userDto.getName(), actualName);
    }
    
    @Test
    public void testLoginUser_SuccessfulLogin() {
        LoginDTO loginDto = new LoginDTO();
        loginDto.setEmail("yateesh.chandra@nucleusteq.com");
        loginDto.setPassword("03072001");
        
        User user = new User();
        user.setEmail(loginDto.getEmail());
        user.setPassword(loginDto.getPassword());
        
        when(userRepository.findByEmail(loginDto.getEmail())).thenReturn(user);
        when(passwordEncoder.matches(loginDto.getPassword(), user.getPassword())).thenReturn(false);
        
        LoginResponse response = userService.loginUser(loginDto);
        assertFalse(response.getStatus());
        assertEquals("Passwords did not match", response.getMessage());
    }
    
    @Test
    public void testLoginUser_IncorrectPassword() {
        LoginDTO loginDto = new LoginDTO();
        loginDto.setEmail("yateesh.chandra@nucleusteq.com");
        loginDto.setPassword("03072001");

        User user = new User();
        user.setEmail(loginDto.getEmail());
        user.setPassword("05072001");
        
        when(userRepository.findByEmail(loginDto.getEmail())).thenReturn(user);
        when(passwordEncoder.matches(loginDto.getPassword(), user.getPassword())).thenReturn(false);
        
        LoginResponse response = userService.loginUser(loginDto);
        assertFalse(response.getStatus());
        assertEquals("Passwords did not match", response.getMessage());
    }
    
    @Test
    public void testLoginUser_EmailNotExist() {
        LoginDTO loginDto = new LoginDTO();
        loginDto.setEmail("yateesh.chandra@nucleusteq.com");
        loginDto.setPassword("03072001");
        when(userRepository.findByEmail(loginDto.getEmail())).thenReturn(null);
        
        LoginResponse response = userService.loginUser(loginDto);
        assertFalse(response.getStatus());
        assertEquals("Email does not exist.!",  response.getMessage());
    }
    
    @Test
    public void testLoginUser_CustomException() {
        LoginDTO loginDto = new LoginDTO();
        loginDto.setEmail("yateesh.chandra@nucleusteq.com");
        loginDto.setPassword("03072001");
        
        when(userRepository.findByEmail(loginDto.getEmail())).thenThrow(CustomException.class);
        assertThrows(CustomException.class, () -> userService.loginUser(loginDto));
    }
    
    
    @Test
    public void testLoginUser_DuplicateKeyException() {
        LoginDTO loginDto = new LoginDTO();
        loginDto.setEmail("yateesh.chandra@nucleusteq.com");
        loginDto.setPassword("03072001");
        when(userRepository.findByEmail(loginDto.getEmail())).thenThrow(DuplicateKeyException.class);
        assertThrows(DuplicateKeyException.class, () -> userService.loginUser(loginDto));
    }
    
    @Test
    public void testAddUserWithInvalidData() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("John Doe");
        userDTO.setEmail("johndoe"); // Invalid email
        userDTO.setPassword("password");
        assertThrows(ValidationException.class, () -> {
            userService.addUser(userDTO);
        });
    }
}
