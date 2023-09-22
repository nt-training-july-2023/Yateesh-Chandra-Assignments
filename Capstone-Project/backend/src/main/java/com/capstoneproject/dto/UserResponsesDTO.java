package com.capstoneproject.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private Long userId;

    /**
     * This is the Quiz Id.
     */
    private Long quizId;

    /**
     * This is the Category Id.
     */
    private Long categoryId;

    /**
     * This is the Number Of Questions.
     */
    @Column(nullable = false)
    private int numOfQuestions;

    /**
     * This is the number of Questions answered.
     */
    @Column(nullable = false)
    private int numOfQuestionsAnswered;

    /**
     * This is the Total Marks.
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
    private String timeStamp;

    /**
     * This is the Time Stamp Setter Method.
     * @return the current Time Stamp.
     */
    public final String setTimeStampMethod() {
        LocalDateTime currentTimeStamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentTimeStamp.format(formatter);
    }
}
