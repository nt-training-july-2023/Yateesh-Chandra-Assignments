/**
 * This package contains the Registration DTO class.
 */
package com.capstoneproject.dto;

import com.capstoneproject.response.ValidationMessages;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is the Data Transfer Object for User Registration.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    /**
     * This is the Id field to be entered by new User.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    /**
     * This is the name field to be entered by new User.
     */
    @Column(name = "user_name", length = ID_MAX_LENGTH, nullable = false)
    @NotBlank(message = ValidationMessages.NAME_NOT_BLANK)
    private String name;

    /**
     * This is the email field to be entered by new User.
     */
    @Column(name = "user_email", unique = true, length = ID_MAX_LENGTH)
    @NotBlank(message = ValidationMessages.EMAIL_NOT_BLANK)
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@nucleusteq\\.com$", message = ValidationMessages.EMAIL_PATTERN)
    private String email;

    /**
     * This is the password field to be entered by new User.
     */
    @Column(name = "password", length = ID_MAX_LENGTH)
    @NotBlank(message = ValidationMessages.PASSWORD_NOT_NULL)
    @Size(min = 6, message = ValidationMessages.PASSWORD_PATTERN)
    private String password;

    /**
     * This is the userRole field which is user by default.
     */
    @Column(name = "user_role", columnDefinition = "varchar(255)"
            + " default 'USER'")
    private String userRole = "USER";

    /**
     * This is the phone Number field to be entered by new User.
     */
    @Column(name = "phone_number", nullable = false)
    @NotBlank(message = ValidationMessages.PHONE_NULL)
    @Pattern(regexp = "\\d{10}", message = ValidationMessages.PHONE_PATTERN)
    private String phoneNumber;

    /**
     * This ID_MAX_LENGTH contains the minimum value to be the value in column.
     */
    private static final int ID_MAX_LENGTH = 255;
}
