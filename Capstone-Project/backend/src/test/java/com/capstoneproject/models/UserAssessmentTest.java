package com.capstoneproject.models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class UserAssessmentTest {

    @Test
    public void testUserAssessmentGetterAndSetter() {
        Long userAssessmentId = 20L;
        User user = new User();
        Question question = new Question();
        String chosenOption = "option2";
        LocalDateTime assessmentTimeStamp = null;
        int score = 10;
        String correctAnswer = "Int";
        boolean isCorrect = true;
        
        UserAssessment userAssessment = new UserAssessment();
        
        userAssessment.setUserAssessmentId(userAssessmentId);
        userAssessment.setUser(user);
        userAssessment.setQuestion(question);
        userAssessment.setChosenOption(chosenOption);
        userAssessment.setAssessmentTimeStamp(assessmentTimeStamp);
        userAssessment.setScore(score);
        userAssessment.setCorrectAnswer(correctAnswer);
        userAssessment.setCorrect(isCorrect);
        
        assertEquals(userAssessmentId, userAssessment.getUserAssessmentId());
        assertEquals(user, userAssessment.getUser());
        assertEquals(question, userAssessment.getQuestion());
        assertEquals(chosenOption, userAssessment.getChosenOption());
        assertEquals(assessmentTimeStamp, userAssessment.getAssessmentTimeStamp());
        assertEquals(score, userAssessment.getScore());
        assertEquals(isCorrect, userAssessment.isCorrect());
    }
    
    @Test
    public void testUserAssessmentAllArgsConstructor() {
        Long userAssessmentId = 20L;
        User user = new User();
        Question question = new Question();
        String chosenOption = "option2";
        LocalDateTime assessmentTimeStamp = null;
        int score = 10;
        String correctAnswer = "Int";
        boolean isCorrect = true;
        UserAssessment userAssessment = new UserAssessment(userAssessmentId, user, question, chosenOption, assessmentTimeStamp, score, correctAnswer, isCorrect);
        
        assertEquals(userAssessmentId, userAssessment.getUserAssessmentId());
        assertEquals(user, userAssessment.getUser());
        assertEquals(question, userAssessment.getQuestion());
        assertEquals(chosenOption, userAssessment.getChosenOption());
        assertEquals(assessmentTimeStamp, userAssessment.getAssessmentTimeStamp());
        assertEquals(score, userAssessment.getScore());
        assertEquals(isCorrect, userAssessment.isCorrect());
    }

}
