/**
 * This contains the Category DTO class.
 */
package com.capstoneproject.dto;

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
  @NotBlank(message = "Category Name is required")
  private String categoryName;
  /**
   * This is Category Description.
   */
  @Column(nullable = false)
  @NotBlank(message = "Category Description is required")
  private String description;
}
