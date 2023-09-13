package com.capstoneproject.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class contains the DTOs for Quiz.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {

    /**
     * This is question Id field that is auto generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;

    /**
     * This is Question Title Field.
     */
    private String questionTitle;

    /**
     * This is Option 1 field.
     */
    private String option1;

    /**
     * This is Option 2 field.
     */
    private String option2;

    /**
     * This is Option 3 field.
     */
    private String option3;

    /**
     * This is Option 4 field.
     */
    private String option4;

    /**
     * This is Correct Option field.
     */
    private String correctOption;

    /**
     * This is Quiz Id from the Quiz.
     */
    private Long quizId;

}
