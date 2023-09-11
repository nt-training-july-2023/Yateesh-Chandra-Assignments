/**
 * This package contains the User Entity.
 */
package com.capstoneproject.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This is the users Entity.
 */
@Entity
@ToString
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
    @Column(name = "user_id", length = ID_MIN_LENGTH)
    private long id;
    /**
     * This is the name column.
     */
    @Column(name = "user_name", length = ID_MAX_LENGTH, nullable = false)
    private String name;
    /**
     * This is the user_email column.
     */
    @Column(name = "user_email", unique = true, length = ID_MAX_LENGTH)
    private String email;
    /**
     * This is the password column.
     */
    @Column(name = "password", length = ID_MAX_LENGTH)
    private String password;
    /**
     * This is the user_role either Administrator or User.
     */
    @Column(name = "user_role", columnDefinition = "varchar(255)"
            + " default 'USER'")
    private String userRole;
    /**
     * This is the phone number column.
     */
    @Column(name = "phone_number")
    private String phoneNumber;
    /**
     * This ID_MIN_LENGTH contains the minimum value to be the value in column.
     */
    private static final int ID_MIN_LENGTH = 45;
    /**
     * This ID_MAX_LENGTH contains the minimum value to be the value in column.
     */
    private static final int ID_MAX_LENGTH = 255;

    @Override
    public final boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name)
                && Objects.equals(email, user.email)
                && Objects.equals(password, user.password)
                && Objects.equals(userRole, user.userRole)
                && Objects.equals(phoneNumber, user.phoneNumber);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(id, name, email, password, userRole, phoneNumber);
    }

    /**
     * This is Single parameter User Constructor.
     * @param userId is User Id.
     * @param userName is user name.
     * @param userEmail is user Email.
     * @param userPassword is User Password.
     * @param role is User Role.
     * @param phone is User Phone.
     */
    public User(final long userId,
            final String userName, final String userEmail,
            final String userPassword, final String role, final String phone) {
        super();
        this.id = userId;
        this.name = userName;
        this.email = userEmail;
        this.password = userPassword;
        this.userRole = role;
        this.phoneNumber = phone;
    }
}
