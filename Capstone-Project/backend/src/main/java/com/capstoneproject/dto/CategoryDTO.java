/**
 * This contains the Category DTO class.
 */
package com.capstoneproject.dto;

import com.capstoneproject.response.ValidationMessages;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This is the class containing DTO class for category.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryDTO {
  /**
   * This is category Id field for Category class.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long categoryId;
  /**
   * This is Category Title Name field.
   */
  @Column(nullable = false, unique = true)
  @NotBlank(message = ValidationMessages.CATEGORY_NAME_NOTBLANK)
  private String categoryName;
  /**
   * This is Category Description.
   */
  @Column(nullable = false)
  @NotBlank(message = ValidationMessages.CATEGORY_DESC_NOTBLANK)
  private String description;
}
