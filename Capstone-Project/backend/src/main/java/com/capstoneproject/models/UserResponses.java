package com.capstoneproject.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This is the User Responses Entity.
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = { "userId", "quizId" }) })
public class UserResponses {

    /**
     * This is the Response Id field.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long responseId;

    /**
     * This joins with a column in User.
     */
    @ManyToOne
    @JoinColumn(name = "userId")
    private User users;

    /**
     * This joins with a column in Quiz.
     */
    @ManyToOne
    @JoinColumn(name = "quizId")
    private Quiz quiz;

    /**
     * This is number of questions field.
     */
    @Column(nullable = false)
    private int numOfQuestions;

    /**
     * This field tells number of questions answered.
     */
    @Column(nullable = false)
    private int numOfQuestionsAnswered;

    /**
     * This is the total marks field.
     */
    @Column(nullable = false)
    private int totalMarks;

    /**
     * This is marks scored.
     */
    @Column(nullable = false)
    private int marksScored;

    /**
     * This field mentions the time stamp for the test.
     */
    private String timeStamp;

    /**
     * This is getter Method for User.
     * @return the User object.
     */
    public final User getUsers() {
        return new User(users.getUserId(), users.getName());
    }

    /**
     * This method sets the users.
     * @param user - User.
     */
    public final void setUsers(final User user) {
        this.users = new User(user.getUserId(), user.getName());
    }

    /**
     * This is getter method for Quiz.
     * @return the new Quiz Object.
     */
    public final Quiz getQuiz() {
        return new Quiz(quiz.getQuizId(), quiz.getQuizName(),
                quiz.getQuizDescription(), quiz.getNumOfQuestions());
    }

    /**
     * This is setter method for Quiz.
     * @param qui - Quiz.
     */
    public final void setQuiz(final Quiz qui) {
        this.quiz = new Quiz(qui.getQuizId(), qui.getQuizName(),
                qui.getQuizDescription(), qui.getNumOfQuestions());
    }

    /**
     * This is User Responses Constructor.
     * @param responseid .
     * @param numOfQuestion .
     * @param numOfQuestionsAnswer .
     * @param totalMark .
     * @param marksScore .
     * @param timestamp .
     */
    public UserResponses(final Long responseid, final int numOfQuestion,
            final int numOfQuestionsAnswer, final int totalMark,
            final int marksScore, final String timestamp) {
        super();
        this.responseId = responseid;
        this.numOfQuestions = numOfQuestion;
        this.numOfQuestionsAnswered = numOfQuestionsAnswer;
        this.totalMarks = totalMark;
        this.marksScored = marksScore;
        this.timeStamp = timestamp;
    }

}
