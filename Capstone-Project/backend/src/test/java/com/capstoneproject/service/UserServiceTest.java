package com.capstoneproject.service;

import com.capstoneproject.dto.LoginDTO;
import com.capstoneproject.dto.UserDTO;
import com.capstoneproject.exceptions.CustomException;
import com.capstoneproject.exceptions.ValidationException;
import com.capstoneproject.models.User;
import com.capstoneproject.repository.UserRepository;
import com.capstoneproject.response.LoginResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addUser_ValidUser_ShouldAddUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Yateesh");
        userDTO.setEmail("yateesh@nucleusteq.com");
        userDTO.setPassword("password123");
        userDTO.setPhoneNumber("1234567890");

        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.empty());
        when(passwordEncoder.encode(userDTO.getPassword())).thenReturn("encodedPassword");

        String result = userService.addUser(userDTO);

        assertEquals("Yateesh", result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void addUser_DuplicateEmail_ShouldThrowDuplicateKeyException() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Yateesh");
        userDTO.setEmail("yateesh@nucleusteq.com");
        userDTO.setPassword("password123");
        userDTO.setPhoneNumber("1234567890");

        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(new User()));

        assertThrows(DuplicateKeyException.class, () -> userService.addUser(userDTO));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void addUser_InvalidData_ShouldThrowValidationException() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(null);
        userDTO.setEmail(null);
        userDTO.setPassword("short");
        userDTO.setPhoneNumber(null);

        assertThrows(ValidationException.class, () -> userService.addUser(userDTO));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void addUser_EmailNotAllowed_ShouldThrowCustomException() {
        UserDTO userDTO = new UserDTO();
        userDTO.setName("Yateesh");
        userDTO.setEmail("yateesh@gmail.com");
        userDTO.setPassword("password123");
        userDTO.setPhoneNumber("1234567890");

        assertThrows(CustomException.class, () -> userService.addUser(userDTO));
    }

    @Test
    void loginUser_ValidCredentials_ShouldReturnLoginResponse() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("yateesh@nucleusteq.com");
        loginDTO.setPassword("password123");

        User user = new User();
        user.setEmail("yateesh@nucleusteq.com");
        user.setPassword("encodedPassword");
        user.setUserRole("USER");
        user.setUserId(1L);
        user.setName("Yateesh");

        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())).thenReturn(true);
        when(userRepository.findOneByEmailAndPassword(loginDTO.getEmail(), user.getPassword())).thenReturn(Optional.of(user));

        LoginResponse response = userService.loginUser(loginDTO);

        assertTrue(response.getStatus());
        assertEquals("USER", response.getUserRole());
        assertEquals(1L, response.getUserId());
        assertEquals("Yateesh", response.getName());
        assertEquals("yateesh@nucleusteq.com", response.getEmail());
    }

    @Test
    void loginUser_InvalidEmail_ShouldReturnLoginResponse() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("nonexistent@nucleusteq.com");
        loginDTO.setPassword("password123");

        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.empty());
        LoginResponse response = userService.loginUser(loginDTO);

        assertFalse(response.getStatus());
        assertEquals("Email does not exist.!", response.getMessage());
    }


    @Test
    void loginUser_InvalidPassword_ShouldReturnLoginResponse() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("yateesh@nucleusteq.com");
        loginDTO.setPassword("wrongPassword");

        User user = new User();
        user.setEmail("yateesh@nucleusteq.com");
        user.setPassword("encodedPassword");

        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())).thenReturn(false);

        LoginResponse response = userService.loginUser(loginDTO);

        assertFalse(response.getStatus());
        assertEquals("Passwords did not match", response.getMessage());
    }
}
