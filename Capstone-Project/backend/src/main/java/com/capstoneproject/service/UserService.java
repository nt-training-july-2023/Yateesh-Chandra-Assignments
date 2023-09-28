package com.capstoneproject.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capstoneproject.dto.LoginDTO;
import com.capstoneproject.dto.UserDTO;
import com.capstoneproject.exceptions.AlreadyExistsException;
import com.capstoneproject.exceptions.ElementNotExistsException;
import com.capstoneproject.exceptions.UnAuthorizedException;
import com.capstoneproject.models.User;
import com.capstoneproject.repository.UserRepository;
import com.capstoneproject.response.LoginResponse;

/**
 * Service class for handling user-related Operations.
 */
@Service
public class UserService {
    /**
     * The repository variable for user related operations.
     */
    @Autowired
    private UserRepository userRepo;

    /**
     * The Encoder variable to encode the password entered.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Add the user.
     *
     * @param userDTO The user data to create.
     * @return the name of the user.
     */
    public final String addUser(final UserDTO userDTO) {
        Optional<User> user = userRepo.findByEmail(userDTO.getEmail());
        if (user.isPresent()) {
            throw new AlreadyExistsException("Email already Exists");
        }
        User users = new User(userDTO.getUserId(), userDTO.getName(),
                    userDTO.getEmail(),
                    this.passwordEncoder.encode(userDTO.getPassword()),
                    userDTO.getUserRole(), userDTO.getPhoneNumber());
        userRepo.save(users);
        return users.getName();
    }

    /**
     * Login a user having the provided credentials.
     *
     * @param loginDTO The Login credentials.
     * @return A LoginResponse object indicating the result of the login
     *         attempt.
     */
    public final LoginResponse loginUser(final LoginDTO loginDTO) {
        String msg = "";
        Optional<User> user1 = userRepo.findByEmail(loginDTO.getEmail());
        if (user1.isPresent()) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.get().getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password,
                    encodedPassword);
            if (isPwdRight) {
                Optional<User> user = userRepo.findOneByEmailAndPassword(
                        loginDTO.getEmail(), encodedPassword);
                if (user.isPresent()) {
                    return new LoginResponse(msg + "Login Successful..!",
                            true, user.get().getUserRole(),
                            user.get().getUserId(), user.get().getName(),
                            user.get().getEmail());
                } else {
                    throw new UnAuthorizedException(msg + "Login Failed");
                }
            } else {
                throw new UnAuthorizedException(msg + "Passwords did not match");
            }
        } else {
            throw new UnAuthorizedException(msg + "Email does not exist.!");
        }
    }

    /**
     * This is the method to find by Email.
     * @param email of String type is taken as input.
     * @return email by finding in the repository.
     */
    public final Optional<User> findByEmail(final String email) {
        return userRepo.findByEmail(email);
    }

    public void deleteUser(final Long userId) {
        userRepo.findById(userId).orElseThrow(
                () -> new ElementNotExistsException("No user found with Id"));
        userRepo.deleteById(userId);
    }
}
