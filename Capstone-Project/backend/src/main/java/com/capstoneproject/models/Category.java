/**
 * The package is for the category model.
 */
package com.capstoneproject.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * This is Category Entity.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "category")
public class Category {
  /**
   * This is the Category ID column that is the primary key.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "category_id")
  private Long categoryId;
  /**
   * This is the category name Column.
   */
  @Column(name = "category_name")
  private String categoryName;
  /**
   * This is the Category Description Column.
   */
  @Column(name = "category_description")
  private String description;
  public Category(Long categoryId, String categoryName, String description) {
    super();
    this.categoryId = categoryId;
    this.categoryName = categoryName;
    this.description = description;
}
/**
   * This is the List of the Quiz mapped by Category.
   */
  @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Quiz> quiz = new ArrayList<>();
 }
