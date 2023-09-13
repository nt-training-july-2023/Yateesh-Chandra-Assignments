/**
 * This Package contains the Class for Question Entity.
 */
package com.capstoneproject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This is the Question entity Class.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "question")
public class Question {
    /**
     * This is the Question Id Column, Primary key.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;
    /**
     * This is the Question Title Column.
     */
    @Column(name = "question_title", nullable = false)
    private String questionTitle;
    /**
     * This is the Option 1 Column.
     */
    @Column(nullable = false)
    private String option1;
    /**
     * This is the Option 2 Column.
     */
    @Column(nullable = false)
    private String option2;
    /**
     * This is the Option 3 Column.
     */
    @Column(nullable = false)
    private String option3;
    /**
     * This is the Option 4 Column.
     */
    @Column(nullable = false)
    private String option4;
    /**
     * This Column contains the correct option.
     */
    @Column(nullable = false)
    private String correctOption;

    /**
     * This is the Quiz Id reference as foreign key from the Quiz Column.
     */
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    /**
     * This gets Quiz.
     * @return quiz.
     */
    public final Quiz getQuiz() {
        return new Quiz(quiz.getQuizId(), quiz.getQuizName(),
                quiz.getQuizDescription(), quiz.getNumOfQuestions());
    }

    /**
     * sets quiz.
     * @param qui is passed.
     */
    public final void setQuiz(final Quiz qui) {
        this.quiz = new Quiz(qui.getQuizId(), qui.getQuizName(),
                qui.getQuizDescription(), qui.getNumOfQuestions());
    }

    /**
     * Question Constructor.
     * @param questionTit title.
     * @param opt1        opt1.
     * @param opt2        opt2.
     * @param opt3        opt3.
     * @param opt4        opt4.
     * @param correctOpt  option that is correct.
     * @param qui Quiz.
     */
    public Question(final String questionTit, final String opt1,
            final String opt2, final String opt3, final String opt4,
            final String correctOpt, final Quiz qui) {
        this.questionTitle = questionTit;
        this.option1 = opt1;
        this.option2 = opt2;
        this.option3 = opt3;
        this.option4 = opt4;
        this.correctOption = correctOpt;
        this.quiz = qui;
    }

    /**
     * This is the single parameter Question.
     * @param otherQuestion is used.
     */
    public Question(final Question otherQuestion) {
        this.questionId = otherQuestion.getQuestionId();
        this.questionTitle = otherQuestion.getQuestionTitle();
        this.option1 = otherQuestion.getOption1();
        this.option2 = otherQuestion.getOption2();
        this.option3 = otherQuestion.getOption3();
        this.option4 = otherQuestion.getOption4();
        this.correctOption = otherQuestion.getCorrectOption();
        this.quiz = otherQuestion.getQuiz();
    }


}
