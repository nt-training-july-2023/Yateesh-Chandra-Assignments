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

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId", "quizId"})
})
public class UserResponses {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long responseId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User users;

    @ManyToOne
    @JoinColumn(name = "quizId")
    private Quiz quiz;

    @Column(nullable = false)
    private int numOfQuestions;

    @Column(nullable = false)
    private int numOfQuestionsAnswered;

    @Column(nullable = false)
    private int totalMarks;

    @Column(nullable = false)
    private int marksScored;

    private String timeStamp;

    public final User getUsers() {
        return new User(users.getUserId(), users.getName());
    }

    public final void setUsers(final User user) {
        this.users = new User(user.getUserId(), user.getName());
    }
    
    public final Quiz getQuiz() {
        return new Quiz(quiz.getQuizId(), quiz.getQuizName(), quiz.getQuizDescription(), quiz.getNumOfQuestions());
    }

    public final void setQuiz(final Quiz qui) {
        this.quiz = new Quiz(qui.getQuizId(), qui.getQuizName(), qui.getQuizDescription(), qui.getNumOfQuestions()); 
    }
}
