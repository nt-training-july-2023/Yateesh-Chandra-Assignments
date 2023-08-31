package com.capstoneproject.models;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class is a Entity class.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAssessment {
    /**
     * This is User Assessment Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_assessmentid")
    private Long userAssessmentId;
    /**
     * This is User Id from the User Entity.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    /**
     * This is Question Id from Question Entity.
     */
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    /**
     * This is the Chosen Option Column.
     */
    @Column(name = "chosen_option")
    private String chosenOption;
    /**
     * This is the Assessment Time Stamp Column.
     */
    @CreationTimestamp
    @Column(name = "assessment_timestamp", updatable = false)
    private LocalDateTime assessmentTimeStamp;
    /**
     * This is the score.
     */
    private int score;
    /**
     * This is the correct Answer column.
     */
    @Column(name = "correct_answer")
    private String correctAnswer;
    /**
     * This checks whether the chosen option is right or wrong.
     */
    @Transient
    @Formula("CASE WHEN chosen_option = question.correct_option "
            + "THEN true ELSE false END")
    private boolean isCorrect;
}
