package com.capstoneproject.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This is the DTO for All Results.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllResultsDTO {

    /**
     * This is the result Id field.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long resultId;

    /**
     * This is the User Id field.
     */
    @Column(nullable = false)
    private Long userId;

    /**
     * This is the email field.
     */
    @Column(nullable = false)
    private String email;

    /**
     * This is the UserName Field.
     */
    @Column(nullable = false)
    private String userName;

    /**
     * This is the category name field.
     */
    @Column(nullable = false)
    private String categoryName;

    /**
     * This is the Quiz Name field.
     */
    @Column(nullable = false)
    private String quizName;

    /**
     * This is the number of Questions field.
     */
    @Column(nullable = false)
    private int numOfQuestions;

    /**
     * This is the Number of Questions Answered field.
     */
    @Column(nullable = false)
    private int numOfQuestionsAnswered;

    /**
     * This is the Total Marks field.
     */
    @Column(nullable = false)
    private int totalMarks;

    /**
     * This is the marks scored field.
     */
    @Column(nullable = false)
    private int marksScored;

    /**
     * This is the Time Stamp field.
     */
    @Column(nullable = false)
    private String timeStamp;
}
