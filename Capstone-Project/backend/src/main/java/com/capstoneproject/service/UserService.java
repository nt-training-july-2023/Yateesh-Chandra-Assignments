package com.capstoneproject.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.capstoneproject.controller.LoginResponse;
import com.capstoneproject.dto.LoginDTO;
import com.capstoneproject.dto.UserDTO;
import com.capstoneproject.exceptions.CustomException;
import com.capstoneproject.exceptions.ValidationException;
import com.capstoneproject.models.User;
import com.capstoneproject.models.UserAssessment;
import com.capstoneproject.repository.UserAssessmentRepository;
import com.capstoneproject.repository.UserRepository;

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
     * This constant is used to prevent Check style Error.
     */
    private static final int NUM = 6;

    /**
     * The Encoder variable to encode the password entered.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * The repository variable is created for User Assessment Operations.
     */
    @Autowired
    private UserAssessmentRepository userAssessmentRepository;

    /**
     * Get the user assessments for the given user.
     *
     * @param user The user to get the assessments for.
     * @return The list of user assessments.
     */
    public final List<UserAssessment> getUserAssessmentByUser(final User user) {
        return userAssessmentRepository.findByUser(user);
    }

    /**
     * Add the user.
     *
     * @param userDTO The user data to create.
     * @return the name of the user.
     */
    public final String addUser(final UserDTO userDTO) {
        try {
            if (userDTO.getUserRole() == null) {
                userDTO.setUserRole("USER");
            }
            if (userDTO.getName() == null || userDTO.getEmail() == null
                    || userDTO.getPassword().length() <= NUM
                    || userDTO.getPhoneNumber() == null) {
                throw new ValidationException("Invalid Data Provided");
            }
            User user = new User(userDTO.getId(), userDTO.getName(),
                    userDTO.getEmail(),
                    this.passwordEncoder.encode(userDTO.getPassword()),
                    userDTO.getUserRole(), userDTO.getPhoneNumber());
            userRepo.save(user);
            return user.getName();
        } catch (DuplicateKeyException e) {
            throw new DuplicateKeyException("Email already exists.");
        } catch (Exception e) {
            throw new ValidationException(
                    "An Error occured while processing the request.");
        }
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
        try {
            User user1 = userRepo.findByEmail(loginDTO.getEmail());
            if (user1 != null) {
                String password = loginDTO.getPassword();
                String encodedPassword = user1.getPassword();
                Boolean isPwdRight = passwordEncoder.matches(password,
                        encodedPassword);
                if (isPwdRight) {
                    Optional<User> user = userRepo.findOneByEmailAndPassword(
                            loginDTO.getEmail(), encodedPassword);
                    if (user.isPresent()) {
                        return new LoginResponse(msg + "Login Successful..!",
                                true, user.get().getUserRole());
                    } else {
                        return new LoginResponse(msg + "Login Failed", false);
                    }
                } else {
                    return new LoginResponse(msg + "Passwords did not match",
                            false);
                }
            } else {
               return new LoginResponse(msg + "Email does not exist.!", false);
            }
        } catch (CustomException e) {
            throw new CustomException(
                    "An error occured whilw processing the request.");
        }
    }
}
