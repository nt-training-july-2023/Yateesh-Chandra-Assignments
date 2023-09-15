package com.capstoneproject.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"userId", "quizId"})
})
public class UserResponsesDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long responseId;

    private Long userId;

    private Long quizId;

    private Long categoryId;

    @Column(nullable = false)
    private int numOfQuestions;

    @Column(nullable = false)
    private int numOfQuestionsAnswered;

    @Column(nullable = false)
    private int totalMarks;

    @Column(nullable = false)
    private int marksScored;

    private String timeStamp;

    public final String setTimeStampMethod() {
        LocalDateTime currentTimeStamp = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentTimeStamp.format(formatter);
    }
}
