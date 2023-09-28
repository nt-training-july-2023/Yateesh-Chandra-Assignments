package com.capstoneproject.service;

import com.capstoneproject.dto.LoginDTO;
import com.capstoneproject.dto.UserDTO;
import com.capstoneproject.exceptions.AlreadyExistsException;
import com.capstoneproject.exceptions.ElementNotExistsException;
import com.capstoneproject.exceptions.UnAuthorizedException;
import com.capstoneproject.models.User;
import com.capstoneproject.repository.UserRepository;
import com.capstoneproject.response.LoginResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

        assertThrows(AlreadyExistsException.class, () -> userService.addUser(userDTO));
        verify(userRepository, never()).save(any(User.class));
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
    void loginUser_InvalidEmail_ShouldThrowUnAuthorizedException() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("nonexistent@nucleusteq.com");
        loginDTO.setPassword("password123");
        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.empty());
        assertThrows(UnAuthorizedException.class, () -> userService.loginUser(loginDTO));
    }

    @Test
    void deleteUser_ExistingUser_ShouldDeleteUser() {
        Long userId = 1L;
        User user = new User();
        user.setUserId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.deleteUser(userId);

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    void deleteUser_NonExistingUser_ShouldThrowElementNotExistsException() {
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ElementNotExistsException.class, () -> userService.deleteUser(userId));

        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, never()).deleteById(userId);
    }

    @Test
    void testLoginUser_InvalidPassword_ShouldThrowUnAuthorizedException() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("yateesh@nucleusteq.com");
        loginDTO.setPassword("wrongPassword");

        User user = new User();
        user.setEmail("yateesh@nucleusteq.com");
        user.setPassword("encodedPassword");

        when(userRepository.findByEmail(loginDTO.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())).thenReturn(false);

        assertThrows(UnAuthorizedException.class, () -> userService.loginUser(loginDTO));
    }

}
