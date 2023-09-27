package com.capstoneproject.dto;

import com.capstoneproject.response.ValidationMessages;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This is the DTO for the User Responses.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponsesDTO {

    /**
     * This is the Response Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long responseId;

    /**
     * This is the User Id.
     */
    @Column(nullable = false)
    @NotNull(message = ValidationMessages.USERID_NOTNULL)
    private Long userId;

    /**
     * This is the Quiz Id.
     */
    @Column(nullable = false)
    @NotNull(message = ValidationMessages.QUIZ_ID_NOTNULL)
    private Long quizId;

    /**
     * This is the Category Id.
     */
    @Column(nullable = false)
    @NotNull(message = ValidationMessages.CATEGORY_ID_NOTNULL)
    private Long categoryId;

    /**
     * This is the Number Of Questions.
     */
    @Column(nullable = false)
    @NotNull(message = ValidationMessages.NUM_OF_QUE_NOTNULL)
    private int numOfQuestions;

    /**
     * This is the number of Questions answered.
     */
    @Column(nullable = false)
    @NotNull(message = ValidationMessages.NUM_OF_QUE_ANSWERED_NOTNULL)
    private int numOfQuestionsAnswered;

    /**
     * This is the Total Marks.
     */
    @Column(nullable = false)
    @NotNull(message = ValidationMessages.TOTAL_MARKS_NOTNULL)
    private int totalMarks;

    /**
     * This is the marks scored field.
     */
    @Column(nullable = false)
    @NotNull(message = ValidationMessages.MARKS_SCORED_NOTNULL)
    private int marksScored;

    /**
     * This is the Time Stamp field.
     */
    @Column(nullable = false)
    private String timeStamp;

}
