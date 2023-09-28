/**
 * This package contains the User Entity.
 */
package com.capstoneproject.models;

import java.util.ArrayList;
import java.util.List;

import com.capstoneproject.response.ValidationMessages;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This is the users Entity.
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    /**
     * This is the user_id column and is primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    /**
     * This is the name column.
     */
    @Column(name = "user_name", length = ID_MAX_LENGTH, nullable = false)
    @NotBlank(message = ValidationMessages.NAME_NOT_BLANK)
    private String name;

    /**
     * This is the user_email column.
     */
    @Column(name = "user_email", unique = true, length = ID_MAX_LENGTH)
    @NotBlank(message = ValidationMessages.EMAIL_NOT_BLANK)
    @Pattern(regexp = "^[a-z0-9]{2,}[.][a-z]{2,}+@nucleusteq.com$", message = "please enter valid username")
    private String email;

    /**
     * This is the password column.
     */
    @Column(name = "password", length = ID_MAX_LENGTH)
    @NotBlank(message = ValidationMessages.PASSWORD_NOT_NULL)
    @Size(min = 6, message = ValidationMessages.PASSWORD_PATTERN)
    private String password;

    /**
     * This is the user_role either Administrator or User.
     */
    @Column(name = "user_role", columnDefinition = "varchar(255)"
            + " default 'USER'")
    private String userRole = "USER";

    /**
     * This is the phone number column.
     */
    @Column(name = "phone_number", nullable = false)
    @NotBlank(message = ValidationMessages.PHONE_NULL)
    @Pattern(regexp = "\\d{10}", message = ValidationMessages.PHONE_PATTERN)
    private String phoneNumber;

    /**
     * This ID_MAX_LENGTH contains the minimum value to be the value in column.
     */
    private static final int ID_MAX_LENGTH = 255;

    /**
     * This joins the column in the User Responses Column.
     */
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<UserResponses> userResponses = new ArrayList<>();

    /**
     * This is the getter method for the User Response.
     * @return the List of User Responses.
     */
    public List<UserResponses> getUserResponses() {
        return new ArrayList<>(userResponses);
    }

    /**
     * This is the setter method for User Responses.
     * @param userResponse - User Response.
     */
    public void setUserResponses(final List<UserResponses> userResponse) {
        this.userResponses = new ArrayList<>(userResponse);
    }

    /**
     * This is Single parameter User Constructor.
     * @param userid is User Id.
     * @param userName is user name.
     * @param userEmail is user Email.
     * @param userPassword is User Password.
     * @param role is User Role.
     * @param phone is User Phone.
     */
    public User(final Long userid,
            final String userName, final String userEmail,
            final String userPassword, final String role, final String phone) {
        super();
        this.userId = userid;
        this.name = userName;
        this.email = userEmail;
        this.password = userPassword;
        this.userRole = role;
        this.phoneNumber = phone;
    }

    /**
     * This is the User Constructor with two parameters.
     * @param userid - Long type.
     * @param userName - String type.
     */
    public User(final Long userid, final String userName) {
        this.userId = userid;
        this.name = userName;
    }
}
