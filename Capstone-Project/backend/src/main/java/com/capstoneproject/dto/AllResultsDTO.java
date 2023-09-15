package com.capstoneproject.dto;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllResultsDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long resultId;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String categoryName;

    @Column(nullable = false)
    private String quizName;

    @Column(nullable = false)
    private int numOfQuestions;
    
    @Column(nullable = false)
    private int numOfQuestionsAnswered;

    @Column(nullable = false)
    private int totalMarks;

    @Column(nullable = false)
    private int marksScored;

    @Column(nullable = false)
    private String timeStamp;
}
