package com.capstoneproject.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * This class contains the DTO for Quiz.
 */
@Getter
@Setter
public class QuizDTO {
    /**
     * This is quizId variable.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;
    /**
     * This is quizName variable.
     */
    private String quizName;
    /**
     * This is Quiz Description variable.
     */
    private String quizDescription;
    /**
     * This field contains the number of Questions variable.
     */
    private int numOfQuestions;
    /**
     * This field contains time in Minutes.
     */
    private int timeInMin;
    /**
     * This is the categoryId variable.
     */
    private Long categoryId;

    /**
     * This is argument.
     */
    public QuizDTO() {
    }

    /**
     * This is parameterized constructor.
     * @param quizid          of Long type.
     * @param quizname        of String type.
     * @param quizdescription of String type.
     * @param numOfquestions  of Integer type.
     * @param time of integer type.
     * @param categoryid      of Long type.
     */
    public QuizDTO(final Long quizid, final String quizname,
            final String quizdescription, final int numOfquestions,
            final int time, final Long categoryid) {
        this.quizId = quizid;
        this.quizName = quizname;
        this.quizDescription = quizdescription;
        this.numOfQuestions = numOfquestions;
        this.timeInMin = time;
        if (categoryid  != null) {
            this.categoryId = categoryid;
        } else {
            this.categoryId = -1L;
        }
    }

    /**
     * This is getter method for Category Id.
     * @return category Id if exists.
     */
    public final Long getCategoryId() {
        if (categoryId != null) {
            return categoryId;
        } else {
            return -1L;
        }
    }
}
