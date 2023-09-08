package com.capstoneproject.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizDTO {
    private Long quizId;
    private String quizName;
    private String quizDescription;
    private int numOfQuestions;
    private Long categoryId;

    public QuizDTO() {
    }

    public QuizDTO(Long quizid, String quizname, String quizdescription, int numOfquestions, Long categoryid) {
        this.quizId = quizid;
        this.quizName = quizname;
        this.quizDescription = quizdescription;
        this.numOfQuestions = numOfquestions;
        this.categoryId = categoryid;
    }
}