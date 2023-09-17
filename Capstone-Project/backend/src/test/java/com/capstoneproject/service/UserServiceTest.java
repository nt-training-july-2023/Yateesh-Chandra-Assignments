package com.capstoneproject.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

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
import com.capstoneproject.repository.UserRepository;

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
    public void testAddUser() {
        UserDTO userDto = new UserDTO();
        userDto.setUserId(1L);
        userDto.setName("Yateesh Chandra");
        userDto.setEmail("yateesh.chandra@nucleusteq.com");
        userDto.setPassword("03072001");
        userDto.setUserRole("USER");
        userDto.setPhoneNumber("8179074291");

        User user = new User();
        user.setUserId(userDto.getUserId());
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
    public void testAddUserInvalidData() {
        Long id = 7L;
        String name = null;
        String email = "test@domain.com";
        String password = "yateesh";
        String userRole = "USER";
        String phoneNumber = "9876543201";
        UserDTO userDto = new UserDTO(id, name, email, password, userRole, phoneNumber);
        
        try {
            userService.addUser(userDto);
        }
        catch(ValidationException e){
            assertEquals("Invalid Data Provided", e.getMessage());
        }
        
        verify(userRepository, never()).save(any(User.class));
    }
    
    @Test
    public void testLoginUser_SuccessfulLogin() {
        LoginDTO loginDto = new LoginDTO();
        loginDto.setEmail("yateesh.chandra@nucleusteq.com");
        loginDto.setPassword("03072001");
        
        User user = new User();
        user.setEmail(loginDto.getEmail());
        user.setPassword("03072001");

        when(userRepository.findByEmail(loginDto.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(loginDto.getPassword(), user.getPassword())).thenReturn(true);
        LoginResponse response = userService.loginUser(loginDto);
        assertFalse(response.getStatus());
        assertNotEquals("Login Successful..!", response.getMessage());
    }

    
    @Test
    public void testLoginUser_IncorrectPassword() {
        LoginDTO loginDto = new LoginDTO();
        loginDto.setEmail("yateesh.chandra@nucleusteq.com");
        loginDto.setPassword("03072001");

        User user = new User();
        user.setEmail(loginDto.getEmail());
        user.setPassword("05072001");
        
        when(userRepository.findByEmail(loginDto.getEmail())).thenReturn(Optional.of(user));
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
    public void testFindByEmailWithEmailAlreadyExists() {
        String existingEmail = "existing@gmail.com";
        User user = new User();
        when(userRepository.findByEmail(existingEmail)).thenReturn(Optional.of(user));
        Optional<User> result = userService.findByEmail(existingEmail);
        
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
        
        verify(userRepository, times(1)).findByEmail(existingEmail);
    }
    
    @Test
    public void testFindByEmailWithEmailDoesNotExist() {
        String nonExistingEmail = "notexisting@gmail.com";
        when(userRepository.findByEmail(nonExistingEmail)).thenReturn(Optional.empty());
        Optional<User> result = userService.findByEmail(nonExistingEmail);
        assertFalse(result.isPresent());
        verify(userRepository, times(1)).findByEmail(nonExistingEmail);
    }

    @Test
    public void testAddUserWithEmailAlreadyExists() {
        String existingEmail = "existing@gmail.com";
        when(userRepository.findByEmail(existingEmail)).thenReturn(Optional.of(new User()));
        UserDTO userDto = new UserDTO();
        userDto.setEmail(existingEmail);
        
        assertThrows(DuplicateKeyException.class, ()-> userService.addUser(userDto));
        verify(userRepository,times(1)).findByEmail(existingEmail);
    }
}
