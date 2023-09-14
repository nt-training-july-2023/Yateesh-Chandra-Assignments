package com.capstoneproject.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class QuizTest {

    @Test
    void testGettersAndSetters() {
        Long quizId = 3L;
        String quizName = "Test Quiz";
        String quizDescription = "Test Quiz Description";
        int numOfQues = 10;
        
        Quiz quiz = new Quiz();
        quiz.setQuizId(quizId);
        quiz.setQuizName(quizName);
        quiz.setQuizDescription(quizDescription);
        quiz.setNumOfQuestions(numOfQues);
        
        assertEquals(quizId, quiz.getQuizId());
        assertEquals(quizName, quiz.getQuizName());
        assertEquals(quizDescription, quiz.getQuizDescription());
        assertEquals(numOfQues, quiz.getNumOfQuestions());
        
    }
    
    @Test
    void testGetQuestionsWhenNoQuestionsSet() {
        Quiz quiz = new Quiz();
        assertEquals(new ArrayList<>(), quiz.getQuestions());
    }
    
    @Test
    void testGetQuestionsWhenQuestionsSet() {
        Quiz quiz = new Quiz();
        List<Question> questions = new ArrayList<>();
        questions.add(new Question());
        quiz.setQuestions(questions);
        assertEquals(questions, quiz.getQuestions());
    }

    @Test
    void testSetQuestionWhenNullQuestions() {
        Quiz quiz = new Quiz();
        assertThrows(NullPointerException.class, () ->  quiz.setQuestions(null));
    }
}
