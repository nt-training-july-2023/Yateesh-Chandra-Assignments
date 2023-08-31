/**
 * This package contains the entity class for quiz.
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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * This class is an entity class for Quiz.
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "quiz")
public class Quiz {
  /**
   * This is the quiz ID column, Primary key.
   */
  @Id
  @Column(name = "quiz_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long quizId;
  /**
   * This is the Quiz name Column.
   */
  @Column(name = "quiz_name")
  private String quizName;
  /**
   * This is the Quiz Description Column.
   */
  @Column(name = "quiz_description")
  private String quizDescription;
  /**
   * This column contains the number of questions of integer type.
   */
  @Column(name = "num_of_questions")
  private int numOfQuestions;
  /**
   * This is Category id column from Category Entity.
   */
  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;
  /**
   * This is Questions List mapped by Quiz.
   */
  @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<Question> questions = new ArrayList<>();
}
