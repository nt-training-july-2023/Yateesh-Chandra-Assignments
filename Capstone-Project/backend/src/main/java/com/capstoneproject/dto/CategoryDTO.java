/**
 * This contains the Category DTO class.
 */
package com.capstoneproject.dto;

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
  private Long categoryId;
  /**
   * This is Category Title Name field.
   */
  private String categoryName;
  /**
   * This is Category Description.
   */
  private String description;
}
