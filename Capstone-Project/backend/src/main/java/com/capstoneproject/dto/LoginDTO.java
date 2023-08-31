/**
 * This package contains the Login DTO class.
 */
package com.capstoneproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * This class contains Data Transfer Objects.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {
  /**
   * This is email we have to input while logging in.
   */
  private String email;
  /**
   * This is password we have to input while logging in.
   */
  private String password;
}
