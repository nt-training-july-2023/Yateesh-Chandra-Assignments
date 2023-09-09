package com.capstoneproject.dto;

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
     * @param categoryid      of Long type.
     */
    public QuizDTO(final Long quizid, final String quizname,
            final String quizdescription, final int numOfquestions,
            final Long categoryid) {
        this.quizId = quizid;
        this.quizName = quizname;
        this.quizDescription = quizdescription;
        this.numOfQuestions = numOfquestions;
        this.categoryId = categoryid;
    }
}
