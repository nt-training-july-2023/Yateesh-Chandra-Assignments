/**
 * This package contains the Registration DTO class.
 */
package com.capstoneproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
public class UserDTO {
  /**
   * This is the Id field to be entered by new User.
   */
  private Long userId;
  /**
   * This is the name field to be entered by new User.
   */
  private String name;
  /**
   * This is the email field to be entered by new User.
   */
  private String email;
  /**
   * This is the password field to be entered by new User.
   */
  private String password;
  /**
   * This is the userRole field which is user by default.
   */
  private String userRole;
  /**
   * This is the phone Number field to be entered by new User.
   */
  private String phoneNumber;
}
